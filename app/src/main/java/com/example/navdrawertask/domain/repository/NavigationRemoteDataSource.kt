package com.example.navdrawertask.domain.repository

import com.example.navdrawertask.data.remote.dto.NavigationResponseDto
import com.example.navdrawertask.data.remote.utils.Resource

interface NavigationRemoteDataSource {
    suspend fun fetchNavigation(): Resource<NavigationResponseDto>
}