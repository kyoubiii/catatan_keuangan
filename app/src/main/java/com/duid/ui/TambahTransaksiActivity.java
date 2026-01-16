package com.duid.ui;

import android.os.Bundle;
import android.widget.*;
import com.duid.R;
import com.duid.model.Transaksi;

public class TambahTransaksiActivity extends BaseActivity { // Menerapkan Inheritance
    private EditText etKet, etJum;
    private RadioGroup rgJenis;
    private Spinner spSumber;
    private int editId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_transaksi);

        initUI();
        checkEditMode();
    }

    private void initUI() {
        etKet = findViewById(R.id.et_keterangan);
        etJum = findViewById(R.id.et_jumlah);
        rgJenis = findViewById(R.id.rg_jenis);
        spSumber = findViewById(R.id.sp_sumber);

        // Contoh pengisian Spinner (Bisa dibuat lebih dinamis nanti)
        String[] sumberArray = {"Cash", "E-Wallet dan Bank"};
        spSumber.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sumberArray));

        // Binding Button Simpan
        findViewById(R.id.btn_simpan).setOnClickListener(v -> handleSave());
    }

    private void checkEditMode() {
        editId = getIntent().getIntExtra("ID_TRANSAKSI", -1);
        if (editId != -1) {
            // Abstraksi: Activity minta data detail lewat ViewModel
            Transaksi t = viewModel.getDetail(editId);
            if (t != null) {
                etKet.setText(t.getKeterangan());
                etJum.setText(String.valueOf((int) t.getJumlah()));
                // Tambahkan logika untuk set RadioButton dan Spinner di sini
            }
        }
    }

    private void handleSave() {
        String ket = etKet.getText().toString();
        String jumStr = etJum.getText().toString();

        // Validasi Sederhana
        if (ket.isEmpty() || jumStr.isEmpty()) {
            Toast.makeText(this, "Data belum lengkap!", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton rb = findViewById(rgJenis.getCheckedRadioButtonId());
        String jenis = rb.getText().toString();
        String sumber = spSumber.getSelectedItem().toString();

        // Pilar 1: Encapsulation - Membuat objek Transaksi baru
        Transaksi t = new Transaksi(ket, Double.parseDouble(jumStr), jenis, sumber);

        if (editId != -1) {
            t.setId(editId); // Set ID untuk mode Update
        }

        // SOLID: Delegasikan urusan simpan ke ViewModel
        viewModel.save(t);

        Toast.makeText(this, "Berhasil disimpan", Toast.LENGTH_SHORT).show();
        finish();
    }
}