package com.example.kursuch.repositories;

import com.example.kursuch.models.Cat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class RepositoryCat implements RepositoryCRUD<Cat>, RepositorySearchByName<Cat> {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public List<Cat> searchByNames(String search){

        List result = em.createNativeQuery("SELECT * FROM cats " +
                        "WHERE name LIKE ?1 OR feline_name LIKE ?2", Cat.class)
                .setParameter(1, search + "%")
                .setParameter(2, search + "%")
                .getResultList();
        return result;
    }

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
    public Cat read(long id) {

        Object cat = em.createNativeQuery("SELECT * FROM cats WHERE id = ?1", Cat.class)
                .setParameter(1, id)
                .getSingleResult();
        return (Cat) cat;
    }

    @Override
    @Transactional
    public List<Cat> readAll() {

        List cats = em.createNativeQuery("SELECT * FROM cats", Cat.class)
                .getResultList();
        return cats;
    }

    @Override
    @Transactional
    public void update(long id, Cat obj) {

        em.createNativeQuery("UPDATE cats SET name = ?2, feline_name = ?3, parod =?4, color = ?5 WHERE id = ?1")
                .setParameter(1, id)
                .setParameter(2, obj.getName())
                .setParameter(3, obj.getNameFeline())
                .setParameter(4, obj.getParod())
                .setParameter(5, checkColor(obj))
                .executeUpdate();
    }

    @Override
    @Transactional
    public void delete(long id) {

        em.createNativeQuery("DELETE FROM cats WHERE id = ?")
                .setParameter(1, id)
                .executeUpdate();
    }

    private Object checkColor(Cat obj){
        //to avoid colling .getColor() from null
        return  (obj.getColor() == null) ? null : obj.getColor().getTitle() ;
    }
}
