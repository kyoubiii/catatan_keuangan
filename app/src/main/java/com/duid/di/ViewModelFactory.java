package com.duid.di;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.duid.repository.ITransaksiRepository;
import com.duid.viewmodel.TransaksiViewModel;
// Dependency Inversion (SOLID - D): Dengan pabrik ini, MainActivity tidak perlu tahu detail cara membuat ViewModel.
// MainActivity cukup minta ke pabrik, dan pabrik yang mengurus detailnya.

// File ini adalah "jembatan" yang memungkinkan data mengalir dari Database -> Repository -> ViewModel.

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final ITransaksiRepository repository;

    public ViewModelFactory(ITransaksiRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TransaksiViewModel.class)) {
            return (T) new TransaksiViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}