package com.example.challenge.githubrepo

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo.api.Response
import com.example.challenge.GithubApiQuery
import com.example.challenge.base.BaseViewModel
import com.example.challenge.repository.AppRepository
import com.example.challenge.response.GitHubRepo
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class GitHubRepoViewModel(application: Application) : BaseViewModel(application) {
    private val appRepository = AppRepository(application)
    val errorLiveData = MutableLiveData<Boolean>()
    val progressBarLiveData = MutableLiveData<Boolean>()

    fun getRepos() {
        errorLiveData.value = false
        progressBarLiveData.value = true
        appRepository.getReposApi()
            .subscribe(object : Observer<Response<GithubApiQuery.Data>> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onNext(t: Response<GithubApiQuery.Data>) {
                    val repos = mutableListOf<GitHubRepo>()
                    t.data?.viewer?.repositories?.nodes?.let {
                        for (repo in it) {

                            repo?.let { node ->
                                val gitHubRepo = GitHubRepo(
                                    node.id,
                                    node.name,
                                    node.description?:"",
                                    node.primaryLanguage?.name?:""
                                )
                                repos.add(gitHubRepo)
                            }

                        }
                        appRepository.insert(repos)
                    }

                }

                override fun onError(e: Throwable) {
                    errorLiveData.value = true
                    progressBarLiveData.value = false
                }

                override fun onComplete() {
                    progressBarLiveData.value = false
                }

            })
    }

    fun getReposCache(): LiveData<List<GitHubRepo>> {
        return appRepository.getReposCache()
    }


}