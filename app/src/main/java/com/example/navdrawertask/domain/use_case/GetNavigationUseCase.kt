package com.example.navdrawertask.domain.use_case

import com.example.navdrawertask.data.remote.dto.MenuDto
import com.example.navdrawertask.domain.model.DrawerDomainModel
import com.example.navdrawertask.domain.model.MenuDomainItem
import com.example.navdrawertask.domain.repository.NavigationRemoteDataSource
import com.example.navdrawertask.data.remote.utils.Resource
import javax.inject.Inject

class GetNavigationUseCase @Inject constructor(
    private val repository: NavigationRemoteDataSource
) {

    suspend operator fun invoke(): Resource<DrawerDomainModel> {
        return when (val result = repository.fetchNavigation()) {
            is Resource.Success -> {
                val menus = result.data.result.menus.orEmpty()
                Resource.Success(splitMenus(menus))
            }

            is Resource.Error -> Resource.Error(result.message)
        }
    }

    private fun splitMenus(menus: List<MenuDto>): DrawerDomainModel {
        val apps = mutableListOf<MenuDomainItem>()
        val help = mutableListOf<MenuDomainItem>()

        var currentSection = MenuSection.UNKNOWN

        menus.forEach { item ->
            if (item.type == 0) {
                currentSection = item.label.toSection()
            } else {
                val domainItem = MenuDomainItem(
                    title = item.label.orEmpty(),
                    iconUrl = item.icon
                )

                when (currentSection) {
                    MenuSection.APPS -> apps.add(domainItem)
                    MenuSection.HELP -> help.add(domainItem)
                    else -> Unit
                }
            }
        }

        return DrawerDomainModel(
            apps = apps,
            help = help
        )
    }
}

enum class MenuSection {
    APPS, HELP, UNKNOWN
}

private fun String?.toSection(): MenuSection {
    return when (this) {
        "APPS" -> MenuSection.APPS
        "HELP & MORE" -> MenuSection.HELP
        else -> MenuSection.UNKNOWN
    }
}




