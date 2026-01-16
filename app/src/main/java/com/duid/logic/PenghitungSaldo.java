package com.duid.logic;

import com.duid.repository.ITransaksiRepository;

// Abstraction, Kontrak untuk semua jenis penghitung saldo
public interface PenghitungSaldo {
    double hitung(ITransaksiRepository repository);
}