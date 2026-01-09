package com.example.navdrawertask.data.local.auth

import com.example.navdrawertask.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class AuthInterceptor @Inject constructor(
    private val tokenProvider: AuthTokenProvider
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", tokenProvider.getToken())
            .build()

        return chain.proceed(request)
    }
}

class AuthTokenProviderImpl @Inject constructor() : AuthTokenProvider {
    override fun getToken(): String = BuildConfig.AUTH_TOKEN
}

