package com.duid.ui;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.duid.di.ViewModelFactory;
import com.duid.repository.TransaksiRepositoryImpl;
import com.duid.viewmodel.TransaksiViewModel;

// Pilar Inheritance, Induk untuk semua Activity
public abstract class BaseActivity extends AppCompatActivity {
    protected TransaksiViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Centralized ViewModel Initialization (DRY Principle)
        ViewModelFactory factory = new ViewModelFactory(new TransaksiRepositoryImpl(this));
        viewModel = new ViewModelProvider(this, factory).get(TransaksiViewModel.class);
    }
}