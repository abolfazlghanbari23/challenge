package com.example.challenge

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "bearer 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" ?: "")
            .build()

        return chain.proceed(request)
    }
}