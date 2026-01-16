package com.duid.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.duid.model.Transaksi;
// database Room dibuat dan dikonfigurasi
// @Database(...): Ini adalah "stempel" konfigurasi
// entities = {Transaksi.class}: memberi tahu Room, "buatkan tabel berdasarkan struktur yang ada di file Transaksi.java"
// Kalau nanti ada tabel baru (misal User), tinggal tambahkan di sini (misal: {Transaksi.class, User.class}).
// version = 1: Ini nomor versi database. jika nanti diubah struktur tabel (tambah kolom), versinya harus dinaikkan jadi 2, dst.
// Ini adalah penerapan Inheritance. Kelas AppDatabase mewarisi semua kemampuan dari kelas induk RoomDatabase milik Google.
// Kelas ini harus abstrak karena kita tidak akan menulis logika detail cara menyimpan datanya (itu tugas Room di belakang layar).
@Database(entities = {Transaksi.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TransaksiDao transaksiDao(); // Ini adalah method untuk mengambil DAO (Data Access Object).

    private static AppDatabase instance; // Variabel Singleton, static: Artinya variabel ini milik kelas, bukan milik objek. Ini digunakan untuk menyimpan satu-satunya salinan database yang aktif di memori HP.

    // Ini menggunakan pola Singleton (OOP) agar database hanya dibuat satu kali
    public static AppDatabase getInstance(Context context) {
        // Logikanya, "Cek dulu, apakah database sudah pernah dibuat? Kalau belum (null), baru kita buat. Kalau sudah ada, pakai yang lama saja."
        if (instance == null) {
            // proses pembuatan database
            // Room.databaseBuilder(...): Ini adalah "tukang bangunan" yang mendirikan database.
            // context: Izin akses ke sistem HP.
            // AppDatabase.class: Cetak biru bangunannya.
            // "duid_database": Nama file database yang akan disimpan di memori HP (seperti nama file .docx atau .mp3).
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "duid_database")
                    .allowMainThreadQueries() // Perintah ini artinya: "Saya izinkan akses database di jalur utama."
                    .build(); // Perintah terakhir untuk menyatukan semua konfigurasi dan mengembalikan objek database yang siap pakai.
        }
        return instance;
    }
}
