package com.duid.viewmodel;

import androidx.lifecycle.ViewModel;
import com.duid.logic.PenghitungSaldo;
import com.duid.model.Transaksi;
import com.duid.repository.ITransaksiRepository;
import java.util.List;

public class TransaksiViewModel extends ViewModel {
    private final ITransaksiRepository repository;

    public TransaksiViewModel(ITransaksiRepository repository) {
        this.repository = repository;
    }

    // PERBAIKAN: Gunakan List biasa (bukan LiveData)
    // Kita panggil langsung dari repo agar data selalu segar saat dipanggil
    public List<Transaksi> getTransactions() {
        return repository.getAll();
    }

    // Tetap menggunakan nama method yang ringkas (SOLID)
    public void save(Transaksi t) {
        repository.save(t);
    }

    public void remove(Transaksi t) {
        repository.delete(t);
    }

    public Transaksi getDetail(int id) {
        return repository.getById(id);
    }

    // Kembali ke double biasa (sesuai interface PenghitungSaldo yang baru)
    public double getBalance(PenghitungSaldo strategi) {
        return strategi.hitung(this.repository);
    }
}