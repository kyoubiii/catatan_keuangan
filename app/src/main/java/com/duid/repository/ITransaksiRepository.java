package com.duid.repository;

import com.duid.model.Transaksi;
import java.util.List;

// abstraksi dan inheritance
public interface ITransaksiRepository {
    List<Transaksi> getAll(); // Pakai List, bukan LiveData
    void save(Transaksi t);
    void delete(Transaksi t);
    Transaksi getById(int id);
    double calculateTotal(String jenis, String sumber); // Pakai double biasa
}