package com.example.navdrawertask.data.remote.api

import com.example.navdrawertask.data.remote.dto.NavigationResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NavigationApiService {

    @GET("sesapi/navigation")
    suspend fun getNavigation(
        @Query("restApi") restApi: String = "Sesapi",
        @Query("sesapi_platform") platform: Int = 1,
        @Query("auth_token") token: String
    ): NavigationResponseDto
}



