package com.example.challenge.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.rx2.rxQuery
import com.example.challenge.GithubApiQuery
import com.example.challenge.UserQuery
import com.example.challenge.response.GitHubRepo
import com.example.challenge.response.User
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppRepository(application: Application) {
    private val appDao: AppDao
    private val allRepos: LiveData<List<GitHubRepo>>
    private val users: LiveData<List<User>>

    init {
        val appDatabase = AppDatabase.getInstance(application)
        appDao = appDatabase.appDao()
        allRepos = appDao.getAllRepos()
        users = appDao.getAllUsers()
    }

    fun getUserApi(): Observable<Response<UserQuery.Data>> {
        val query = UserQuery()
        return Apollo.instance!!.rxQuery(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {  }
    }

    fun getReposApi(): Observable<Response<GithubApiQuery.Data>> {
        val query = GithubApiQuery()
        return Apollo.instance!!.rxQuery(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {  }

    }

    fun getReposCache(): LiveData<List<GitHubRepo>> {
        return appDao.getAllRepos()
    }


    fun getUsersCache(): LiveData<List<User>> {
        return appDao.getAllUsers()
    }

    fun insert(user: User) {
        appDao.insert(user)
    }

    fun insert(repos: List<GitHubRepo>) {
        appDao.insert(repos)
    }


}