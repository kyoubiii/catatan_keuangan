package com.duid.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.duid.model.Transaksi;

@Database(entities = {Transaksi.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TransaksiDao transaksiDao();

    private static AppDatabase instance;

    // Ini menggunakan pola Singleton (OOP) agar database hanya dibuat satu kali
    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "duid_database")
                    .allowMainThreadQueries() // Hanya untuk belajar, nanti kita ganti
                    .build();
        }
        return instance;
    }
}
