package com.duid.logic;

import com.duid.repository.ITransaksiRepository;

// polimorfisme dan abstraksi
// SRP, satu kelas satu fungsi
public class SaldoCash implements PenghitungSaldo {
    @Override
    public double hitung(ITransaksiRepository repository) {
        double masuk = repository.calculateTotal("Pemasukan", "Cash");
        double keluar = repository.calculateTotal("Pengeluaran", "Cash");
        return masuk - keluar;
    }
}