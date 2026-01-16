package com.duid.repository;

import android.content.Context;
import com.duid.database.AppDatabase;
import com.duid.database.TransaksiDao;
import com.duid.model.Transaksi;
import java.util.List;

// polimorfisme dan inheritance
public class TransaksiRepositoryImpl implements ITransaksiRepository {
    private final TransaksiDao dao;

    public TransaksiRepositoryImpl(Context context) {
        dao = AppDatabase.getInstance(context).transaksiDao();
    }

    // ERROR 1: Metode ini hilang di kode kamu
    @Override
    public List<Transaksi> getAll() {
        return dao.getAll(); // Mengambil List biasa, bukan LiveData
    }

    @Override
    public void save(Transaksi t) {
        if (t.getId() == 0) {
            dao.insert(t);
        } else {
            dao.update(t);
        }
    }

    @Override
    public void delete(Transaksi t) {
        dao.delete(t);
    }

    @Override
    public Transaksi getById(int id) {
        return dao.getById(id);
    }

    @Override
    public double calculateTotal(String jenis, String sumber) {
        return dao.getSumByJenisAndSumber(jenis, sumber);
    }
}