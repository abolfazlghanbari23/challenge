package com.example.challenge.githubrepo

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo.api.Response
import com.example.challenge.GithubApiQuery
import com.example.challenge.UserQuery
import com.example.challenge.base.BaseViewModel
import com.example.challenge.repository.AppRepository
import com.example.challenge.response.GitHubRepo
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class GitHubRepoViewModel(application: Application) : BaseViewModel(application) {
    private val appRepository = AppRepository(application)
    val errorLiveData = MutableLiveData<Boolean>()

    fun getRepos() {
        appRepository.getReposApi()
            .subscribe(object : Observer<Response<GithubApiQuery.Data>> {
                override fun onSubscribe(d: Disposable) {
                    Log.d("TAG", "onSubscribe: ")
                }

                override fun onNext(t: Response<GithubApiQuery.Data>) {
                    Log.d("TAG", "onNext: ")
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG", "onError: ")
                }

                override fun onComplete() {
                    Log.d("TAG", "onComplete: ")
                }

            })
    }

    fun getReposCache(): LiveData<List<GitHubRepo>> {
        return appRepository.getReposCache()
    }


}