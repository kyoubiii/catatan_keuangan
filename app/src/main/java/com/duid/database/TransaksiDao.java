package com.duid.database;

import androidx.room.Dao;
import androidx.room.Query;
import com.duid.model.Transaksi;
import java.util.List;
import androidx.lifecycle.LiveData;

@Dao
// Menerapkan Inheritance dari BaseDao
public interface TransaksiDao extends BaseDao<Transaksi> {

    @Query("SELECT * FROM tabel_transaksi")
    List<Transaksi> getAll();

    @Query("SELECT * FROM tabel_transaksi WHERE id = :id LIMIT 1")
    Transaksi getById(int id);

    @Query("SELECT SUM(jumlah) FROM tabel_transaksi WHERE jenis = :jenis AND sumber = :sumber")
    double getSumByJenisAndSumber(String jenis, String sumber);
}