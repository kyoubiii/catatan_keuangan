package com.duid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.duid.R;
import com.duid.model.Transaksi;
import java.util.List;

public class MainActivity extends BaseActivity { // Pilar 3: Inheritance

    private RecyclerView rvTransaksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvTransaksi = findViewById(R.id.rv_transaksi);
        rvTransaksi.setLayoutManager(new LinearLayoutManager(this));

        setupButtons();
        // Tidak ada lagi observeData() di sini
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Cara manual: Ambil dan tampilkan data setiap kali aplikasi terlihat di layar
        refreshData();
    }

    private void refreshData() {
        // Mengambil List transaksi biasa dari ViewModel
        List<Transaksi> list = viewModel.getTransactions();
        updateUI(list);
    }

    private void updateUI(List<Transaksi> list) {
        TransaksiAdapter adapter = new TransaksiAdapter(list);

        // SRP: Activity tetap mengontrol logika navigasi dan interaksi
        adapter.setOnTransaksiClickListener(new TransaksiAdapter.OnTransaksiClickListener() {
            @Override
            public void onEdit(Transaksi t) {
                Intent intent = new Intent(MainActivity.this, TambahTransaksiActivity.class);
                intent.putExtra("ID_TRANSAKSI", t.getId());
                startActivity(intent);
            }

            @Override
            public void onDelete(Transaksi t) {
                showDeleteDialog(t);
            }
        });

        rvTransaksi.setAdapter(adapter);
    }

    private void showDeleteDialog(Transaksi transaksi) {
        new AlertDialog.Builder(this)
                .setTitle("Hapus Transaksi")
                .setMessage("Yakin ingin menghapus " + transaksi.getKeterangan() + "?")
                .setPositiveButton("Ya", (dialog, which) -> {
                    // Eksekusi hapus di database melalui ViewModel
                    viewModel.remove(transaksi);

                    // PENTING: Panggil refreshData secara manual karena datanya tidak otomatis update
                    refreshData();

                    Toast.makeText(this, "Berhasil dihapus", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Batal", null)
                .show();
    }

    private void setupButtons() {
        findViewById(R.id.fab_add).setOnClickListener(v ->
                startActivity(new Intent(this, TambahTransaksiActivity.class)));

        findViewById(R.id.btn_ke_saldo).setOnClickListener(v ->
                startActivity(new Intent(this, SaldoActivity.class)));
    }
}