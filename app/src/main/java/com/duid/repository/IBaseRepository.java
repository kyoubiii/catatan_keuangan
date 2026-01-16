package com.duid.repository;

import java.util.List;

// Pilar 3: Inheritance - Template dasar untuk semua Repository di masa depan
public interface IBaseRepository<T> {
    List<T> getAll();
    void save(T obj);
    void delete(T obj);
    T getById(int id);
}