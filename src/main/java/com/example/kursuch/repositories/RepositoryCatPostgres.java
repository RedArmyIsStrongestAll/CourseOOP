package com.example.kursuch.repositories;

import com.example.kursuch.models.Cat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class RepositoryCatPostgres implements Repository<Cat> {

    @PersistenceContext
    EntityManager em;

    @Transactional
    @Override
    public void create(Cat obj) {

        em.createNativeQuery("INSERT INTO cats (id, name, feline_name, color, parod) " +
                                "VALUES (nextval('sequence_for_cats'), ?1, ?2, ?3, ?4) ")
                .setParameter(1, obj.getName()) //name
                .setParameter(2, obj.getNameFeline()) //feline name
                .setParameter(3, checkColor(obj)) //color
                .setParameter(4, obj.getParod()) //parod
                .executeUpdate();
    }

    @Transactional
    @Override
    public Optional<Cat> read(long id) {

        /*Optional result = Optional.of( em.createNativeQuery("Select *" +
                                                            "From cats" +
                                                            "WHERE id = 1", Cat.class)
                .getSingleResult() );*/

        Optional result = Optional.of(em.createQuery("SELECT cat from cats cat where cat.id = ?1", Cat.class)
                .setParameter(1, id)
                .getSingleResult());

        return result;
    }

    @Override
    @Transactional
    public Optional<List<Cat>> readAll() {

        Optional result = Optional.of(em.createNativeQuery("SELECT *" +
                                                                "FROM cats", Cat.class)
                .getResultList() );

        return result;
    }

    @Override
    public void update(long id, Cat obj) {
        if (readAll().orElse(null) != null) {
            List<Cat> listCat = readAll().orElse(null);
            listCat.stream().filter(cat -> cat.getId() == id)
                    .peek(cat -> {
                        cat.setName(obj.getName());
                        cat.setNameFeline(obj.getNameFeline());
                        cat.setColor(obj.getColor());
                        cat.setParod(obj.getParod());
                        create(cat);
                    });
        }
    }

    @Override
    public void delete(long id) {

    }

    private Object checkColor(Cat obj){
        return  (obj.isEmptyColor()) ? null : obj.getColor().getTitle() ;
    }
}
