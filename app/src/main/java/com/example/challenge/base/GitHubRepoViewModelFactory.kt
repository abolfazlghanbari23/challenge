package com.example.challenge.base

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModel
import com.example.challenge.githubrepo.GitHubRepoViewModel

class GitHubRepoViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GitHubRepoViewModel(application) as T
    }
}