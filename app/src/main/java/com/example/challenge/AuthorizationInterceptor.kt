package com.example.challenge

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "bearer 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")
            .build()

        return chain.proceed(request)
    }
}