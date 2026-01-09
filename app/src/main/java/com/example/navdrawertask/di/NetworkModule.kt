package com.example.navdrawertask.di

import com.example.navdrawertask.BuildConfig
import com.example.navdrawertask.data.local.auth.AuthInterceptor
import com.example.navdrawertask.data.local.auth.AuthTokenProvider
import com.example.navdrawertask.data.local.auth.AuthTokenProviderImpl
import com.example.navdrawertask.data.remote.api.NavigationApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideAuthInterceptor(tokenProvider: AuthTokenProvider): AuthInterceptor =
        AuthInterceptor(tokenProvider)

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideNavigationApi(retrofit: Retrofit): NavigationApiService =
        retrofit.create(NavigationApiService::class.java)
}
