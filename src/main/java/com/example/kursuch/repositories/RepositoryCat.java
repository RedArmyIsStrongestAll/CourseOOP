package com.example.kursuch.repositories;

import com.example.kursuch.customType.color.Color;
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
        List result = em.createQuery("SELECT c FROM cats c " +
                        "WHERE c.name LIKE ?1 OR c.nameFeline LIKE ?2", Cat.class)
                .setParameter(1, search + "%")
                .setParameter(2, search + "%")
                .getResultList();
        return result;
    }

    @Transactional
    @Override
    public void create(Cat obj) {
        em.persist(obj);
    }

    @Transactional
    @Override
    public Cat read(long id) {
        Object cat = em.createQuery("SELECT c FROM cats c WHERE c.id = ?1", Cat.class)
                .setParameter(1, id)
                .getSingleResult();
        return (Cat) cat;
    }

    @Override
    @Transactional
    public List<Cat> readAll() {
        List cats = em.createQuery("SELECT c FROM cats c", Cat.class)
                .getResultList();
        return cats;
    }

    @Override
    @Transactional
    public void update(long id, Cat obj) {
        em.createQuery("UPDATE cats SET name = ?2, nameFeline = ?3, parod =?4, color = ?5 WHERE id = ?1")
                .setParameter(1, id)
                .setParameter(2, obj.getName())
                .setParameter(3, obj.getNameFeline())
                .setParameter(4, obj.getParod())
                .setParameter(5, obj.getColor())
                .executeUpdate();
    }

    @Override
    @Transactional
    public void delete(long id) {
        em.createQuery("DELETE FROM cats WHERE id = ?1")
                .setParameter(1, id)
                .executeUpdate();
    }
}
