
package com.example.navdrawertask.presentation.navigation_drawer.screens

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.navdrawertask.R
import com.example.navdrawertask.data.remote.utils.Resource
import com.example.navdrawertask.presentation.navigation_drawer.model.DrawerUiState
import com.example.navdrawertask.presentation.navigation_drawer.model.DrawerUserUi
import com.example.navdrawertask.presentation.navigation_drawer.screensimport.NavigationDrawerScreen
import com.example.navdrawertask.presentation.navigation_drawer.viewmodel.MenusViewModel
import com.example.navdrawertask.ui.theme.ScreenBgColor
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val menuViewModel: MenusViewModel = hiltViewModel()
    val uiState by menuViewModel.uiState.collectAsStateWithLifecycle()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    DrawerStatusBarColor(
        color = ScreenBgColor,
        darkIcons = true
    )
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.fillMaxWidth()) {
                DrawerContent(uiState = uiState)
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.home)) },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = stringResource(R.string.menu)
                            )
                        }
                    },
                )
            },
            ) { padding ->
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(padding), contentAlignment = Alignment.Center
            ) {
                Text(stringResource(R.string.home_screen))
            }
        }
    }
}

@Composable
private fun DrawerContent(
    uiState: DrawerUiState
) {
    ModalDrawerSheet(
        modifier = Modifier.fillMaxWidth()
    ) {
        when {
            uiState.isLoading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            uiState.error != null -> {
                val errorMessage = (uiState as Resource.Error).message
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(errorMessage)
                }
            }

            else -> {
                NavigationDrawerScreen(
                    user = DrawerUserUi(
                        name = "Guest User", profileUrl = "https://i.pravatar.cc/150"
                    ),
                    appItems = uiState.apps,
                    helpItems = uiState.help,
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun DrawerStatusBarColor(
    color: Color,
    darkIcons: Boolean = true
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window ?: return@SideEffect
            window.statusBarColor = color.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkIcons
        }
    }
}

