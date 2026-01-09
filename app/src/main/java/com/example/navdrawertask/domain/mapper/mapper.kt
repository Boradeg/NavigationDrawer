package com.example.navdrawertask.domain.mapper

import com.example.navdrawertask.domain.model.DrawerDomainModel
import com.example.navdrawertask.presentation.navigation_drawer.model.DrawerUiState
import com.example.navdrawertask.presentation.navigation_drawer.model.MenuItemUi

 fun DrawerDomainModel.toUiState(): DrawerUiState {
    return DrawerUiState(
        apps = apps.map { MenuItemUi(it.title, it.iconUrl) },
        help = help.map { MenuItemUi(it.title, it.iconUrl) }
    )
}