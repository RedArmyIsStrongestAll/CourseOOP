package com.example.kursuch.repositories;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    void create (T obj);
    Optional<T> read (long id);
    Optional<List<T>> readAll ();
    void update (long id, T obj);
    void delete (long id);
}
