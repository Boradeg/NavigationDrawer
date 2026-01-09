package com.example.navdrawertask.data.remote.repository

import com.example.navdrawertask.data.local.auth.AuthTokenProvider
import com.example.navdrawertask.data.remote.api.NavigationApiService
import com.example.navdrawertask.data.remote.dto.NavigationResponseDto
import com.example.navdrawertask.domain.repository.NavigationRemoteDataSource
import com.example.navdrawertask.data.remote.utils.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NavigationRemoteDataSourceImpl @Inject constructor(
    private val api: NavigationApiService,
    private val tokenProvider: AuthTokenProvider
) : NavigationRemoteDataSource {

    override suspend fun fetchNavigation(): Resource<NavigationResponseDto> {
        return try {
            val response = api.getNavigation(
                token = tokenProvider.getToken()
            )
            Resource.Success(response)
        } catch (e: HttpException) {
            Resource.Error("Server error: ${e.code()}")
        } catch (e: IOException) {
            Resource.Error("No internet connection")
        } catch (e: Exception) {
            Resource.Error("Something went wrong")
        }
    }
}
