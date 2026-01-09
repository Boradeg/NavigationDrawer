package com.example.navdrawertask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.navdrawertask.presentation.navigation.AppNavGraph
import com.example.navdrawertask.ui.theme.NavDrawerTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavDrawerTaskTheme {
                AppNavGraph()
            }
        }
    }
}



