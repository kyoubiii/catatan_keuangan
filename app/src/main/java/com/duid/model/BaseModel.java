package com.duid.model;

import androidx.room.PrimaryKey;

// Abstraksi dasar untuk semua model di masa depan
public abstract class BaseModel {
    @PrimaryKey(autoGenerate = true)
    protected int id;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
}