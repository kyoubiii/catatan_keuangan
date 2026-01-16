package com.duid.model;

import android.graphics.Color;
import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(tableName = "tabel_transaksi")
public class Transaksi extends BaseModel { // Menerapkan Inheritance


    public static final String JENIS_PENGELUARAN = "Pengeluaran";
    public static final String JENIS_PEMASUKAN = "Pemasukan";

    private String keterangan;
    private double jumlah;
    private String jenis;
    private String sumber;

    public Transaksi(String keterangan, double jumlah, String jenis, String sumber) {
        // Enkapsulasi: Tambahkan validasi sederhana
        this.keterangan = (keterangan == null || keterangan.isEmpty()) ? "Tanpa Keterangan" : keterangan;
        this.jumlah = Math.max(jumlah, 0); // Jumlah tidak boleh negatif
        this.jenis = jenis;
        this.sumber = sumber;
    }

    // Logika Bisnis (Encapsulation)
    public String getFormattedJumlah() {
        return "Rp " + String.format("%,.0f", this.jumlah);
    }

    public int getColorByJenis() {
        // Menggunakan konstanta agar lebih aman
        return JENIS_PENGELUARAN.equals(this.jenis) ? Color.RED : Color.parseColor("#4CAF50");
    }


    public String getKeterangan() {
        return keterangan;
    }
    public double getJumlah() {
        return jumlah;
    }
    public String getJenis() {
        return jenis;
    }
    public String getSumber() {
        return sumber;
    }

    // Setter untuk Room (Encapsulation)
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
    public void setJumlah(double jumlah) {
        this.jumlah = Math.max(jumlah, 0);
    }
    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
    public void setSumber(String sumber) {
        this.sumber = sumber;
    }
}