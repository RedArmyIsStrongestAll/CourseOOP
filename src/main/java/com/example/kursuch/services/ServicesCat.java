package com.example.kursuch.services;

import com.example.kursuch.customType.color.Color;
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
        if(checkNoValidityName(obj)){
            throw new RuntimeException("Хоть одно то имя должно быть у кота");
        }
        repository.create(obj);

    }

    @Override
    public Cat read(long id) {
        if (id == 0){
            throw new RuntimeException("Такого кота пока нет...");
        }

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
        if(checkNoValidityName(obj)){
            throw new RuntimeException("Хоть одно то имя должно быть у кота");
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
        return cats;
    }

    private boolean checkNoValidityName(Cat cat){
        boolean firstName = cat.getName() != "";
        boolean secondName = cat.getNameFeline() != "";
        return !(firstName || secondName);
    }

    public void returnNullValues (Cat cat){
        if ((cat.getName()).equals("неизвестно")){
            cat.setName(null);
        }
        if ((cat.getNameFeline()).equals("неизвестно")){
            cat.setNameFeline(null);
        }
        if ((cat.getParod()).equals("неизвестно")){
            cat.setParod(null);
        }
        if (cat.getColor() == Color.NO){
            cat.setColor(null);
        }
    }
}
