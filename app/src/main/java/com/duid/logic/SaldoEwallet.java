package com.duid.logic;

import com.duid.repository.ITransaksiRepository;

// polimorfisme dan abstraksi
// SRP, satu kelas satu fungsi
public class SaldoEwallet implements PenghitungSaldo {
    @Override
    public double hitung(ITransaksiRepository repository) {
        double masuk = repository.calculateTotal("Pemasukan", "E-Wallet");
        double keluar = repository.calculateTotal("Pengeluaran", "E-Wallet");
        return masuk - keluar;
    }
}