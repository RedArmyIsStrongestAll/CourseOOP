package com.example.kursuch.repositories;

import java.util.List;
import java.util.Optional;

public interface RepositoryCRUD<T> {

    void create (T obj);

    T read (long id);

    List<T> readAll ();

    void update (long id, T obj);

    void delete (long id);

}
