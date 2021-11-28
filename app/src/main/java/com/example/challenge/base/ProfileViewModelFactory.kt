package com.example.challenge.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.challenge.profile.ProfileViewModel;

public class ProfileViewModelFactory implements ViewModelProvider.Factory {
    private Application application;


    public ProfileViewModelFactory(Application application) {
        this.application = application;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ProfileViewModel(application);
    }
}

