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