package com.duid.database;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

// Pilar 3: Inheritance - Template dasar untuk semua DAO
public interface BaseDao<T> {
    @Insert
    void insert(T obj);

    @Update
    void update(T obj);

    @Delete
    void delete(T obj);
}