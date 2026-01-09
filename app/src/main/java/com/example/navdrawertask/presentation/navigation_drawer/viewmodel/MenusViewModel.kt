package com.example.navdrawertask.presentation.navigation_drawer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navdrawertask.domain.mapper.toUiState
import com.example.navdrawertask.domain.use_case.GetNavigationUseCase
import com.example.navdrawertask.data.remote.utils.Resource
import com.example.navdrawertask.presentation.navigation_drawer.model.DrawerUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenusViewModel @Inject constructor(
    private val getNavigationUseCase: GetNavigationUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DrawerUiState())
    val uiState: StateFlow<DrawerUiState> = _uiState.asStateFlow()

    init {
        fetchNavigation()
    }

    private fun fetchNavigation() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            when (val result = getNavigationUseCase()) {
                is Resource.Success -> {
                    _uiState.value = result.data.toUiState()
                }

                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }

}



