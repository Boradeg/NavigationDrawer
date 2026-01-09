package com.example.navdrawertask.di

import com.example.navdrawertask.data.local.auth.AuthTokenProvider
import com.example.navdrawertask.data.local.auth.AuthTokenProviderImpl
import com.example.navdrawertask.data.remote.repository.NavigationRemoteDataSourceImpl
import com.example.navdrawertask.domain.repository.NavigationRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthTokenRepository(
        impl: AuthTokenProviderImpl
    ): AuthTokenProvider

    @Binds
    @Singleton
    abstract fun bindNavigationRemoteDataSource(
        impl: NavigationRemoteDataSourceImpl
    ): NavigationRemoteDataSource
}
