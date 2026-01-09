package com.example.navdrawertask.data.local.auth

interface AuthTokenProvider {
    fun getToken(): String
}