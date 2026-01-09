package com.example.navdrawertask.data.remote.dto

data class ResultDto(
    val friend_req_count: Int? = null,
    val loggedin_user_id: Int? = null,
    val menus: List<MenuDto>? = null,
    val message_count: Int? = null,
    val notification_count: Int? = null
)
