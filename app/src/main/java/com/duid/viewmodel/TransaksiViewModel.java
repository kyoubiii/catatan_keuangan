package com.duid.viewmodel;

import androidx.lifecycle.ViewModel;
import com.duid.logic.PenghitungSaldo;
import com.duid.model.Transaksi;
import com.duid.repository.ITransaksiRepository;
import java.util.List;

// Tanpa file ini, kita harus menulis kode database (dao.insert, dao.query) langsung di dalam MainActivity.
// Itu akan bikin MainActivity jadi Kusut (Spaghetti Code).
// Dengan ViewModel, MainActivity kamu jadi bersih dan cuma isinya kode UI saja
public class TransaksiViewModel extends ViewModel {
    private final ITransaksiRepository repository;

    public TransaksiViewModel(ITransaksiRepository repository) {
        this.repository = repository;
    }


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

    public double getBalance(PenghitungSaldo strategi) {
        return strategi.hitung(this.repository);
    }
}