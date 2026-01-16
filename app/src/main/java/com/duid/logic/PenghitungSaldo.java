package com.duid.logic;

import com.duid.repository.ITransaksiRepository;

// Pilar 2: Abstraction - Kontrak untuk semua jenis penghitung saldo
public interface PenghitungSaldo {
    double hitung(ITransaksiRepository repository);
}