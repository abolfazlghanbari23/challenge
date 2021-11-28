package com.example.challenge.repository

import com.apollographql.apollo.ApolloClient
import com.example.challenge.AuthorizationInterceptor
import okhttp3.OkHttpClient

class Apollo {
    companion object {
        var instance: ApolloClient? = null
            get() {
                if (field == null) {
                    instance = ApolloClient.builder()
                        .okHttpClient(
                            OkHttpClient.Builder()
                                .addInterceptor(AuthorizationInterceptor())
                                .build()
                        ).serverUrl("https://api.github.com/graphql")
                        .build()
                }
                return field
            }
    }
}