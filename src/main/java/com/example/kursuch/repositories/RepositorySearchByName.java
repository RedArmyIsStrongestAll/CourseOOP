package com.example.kursuch.repositories;

import com.example.kursuch.models.Cat;

import java.util.List;

public interface RepositorySearchByName<T> {

    public List<T> searchByNames(String search);

}
