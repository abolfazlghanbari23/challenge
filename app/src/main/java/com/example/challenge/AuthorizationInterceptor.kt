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