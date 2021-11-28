package com.example.challenge.profile

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.rx2.Rx2Apollo
import com.example.challenge.UserQuery
import com.example.challenge.base.BaseViewModel
import com.example.challenge.repository.AppRepository
import com.example.challenge.response.User
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class ProfileViewModel(application: Application) : BaseViewModel(application) {
    private val appRepository = AppRepository(application)
    val errorLiveData = MutableLiveData<Boolean>()
    val progressBarLiveData = MutableLiveData<Boolean>()

    fun getUser() {
        errorLiveData.value = false
        progressBarLiveData.value = true
        appRepository.getUserApi()
            .subscribe(object : Observer<Response<UserQuery.Data>> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onNext(t: Response<UserQuery.Data>) {
                    t.data?.viewer?.let { viewer ->
                        val user = User(
                            id = viewer.id,
                            avatarUrl = viewer.avatarUrl as String,
                            name = viewer.name?: "",
                            email = viewer.email,
                            followers = viewer.followers.totalCount,
                            following = viewer.following.totalCount
                        )
                        appRepository.insert(user)
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

    fun getUserCache(): LiveData<List<User>> {
        return appRepository.getUsersCache()
    }

}