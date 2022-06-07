package com.example.kursuch.services;

import com.example.kursuch.models.Cat;
import com.example.kursuch.repositories.RepositoryCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesCat implements ServiceForCRUD<Cat>, ServiceForSearch<Cat>{

    private final RepositoryCat repository;

    @Autowired
    public ServicesCat(RepositoryCat repository) {
        this.repository = repository;
    }

    @Override
    public void create(Cat obj) {

        if(obj.getNameFeline() == null){
            throw new RuntimeException("Опрометчиво думать, что у кота может не быть своего собственного кашачьего имени...");
        }
        repository.create(obj);
    }

    @Override
    public Cat read(long id) {

        Cat cat = repository.read(id);
        if (cat == null){
            throw new RuntimeException("Такого кота пока нет...");
        }
        return cat;
    }

    @Override
    public List<Cat> readAll() {

        List<Cat> cats = repository.readAll();
        if (cats == null){
            throw new RuntimeException("Нет котов!");
        }
        return cats;
    }

    @Override
    public void update(long id, Cat obj) {

        read(id);
        if(obj.getNameFeline() == null){
            throw new RuntimeException("Опрометчиво думать, что у кота может не быть своего собственного кашачьего имени...");
        }
        repository.update(id, obj);
    }

    @Override
    public void delete(long id) {
        read(id);
        repository.delete(id);
    }

    @Override
    public List<Cat> searchByNames(String search) {
        readAll();
        List<Cat> cats = repository.searchByNames(search);
        System.out.println("сервис поиска: " + cats);
        return cats;
    }
}
