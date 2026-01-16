package com.duid.database;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

// Pilar Inheritance, Template dasar untuk semua DAO
// Kita tidak bisa bicara langsung dengan Database (yang bahasanya SQL). DAO adalah perantaranya.
public interface BaseDao<T> {
    @Insert
    void insert(T obj);

    @Update
    void update(T obj);

    @Delete
    void delete(T obj);
}