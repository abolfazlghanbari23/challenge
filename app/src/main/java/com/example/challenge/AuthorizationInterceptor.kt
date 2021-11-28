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