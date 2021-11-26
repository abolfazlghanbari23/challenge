package com.example.challenge.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.challenge.response.GitHubRepo

class AppRepository(application: Application) {
    private val appDao: AppDao
    private val allRepos: LiveData<List<GitHubRepo>>

    init {
        val appDatabase = AppDatabase.getInstance(application)
        appDao = appDatabase.appDao()
        allRepos = appDao.getAllRepos()
    }


}