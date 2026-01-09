package com.example.navdrawertask.presentation.navigation_drawer.model

data class DrawerUiState(
    val isLoading: Boolean = false,
    val apps: List<MenuItemUi> = emptyList(),
    val help: List<MenuItemUi> = emptyList(),
    val error: String? = null
)
