package com.google.samples.apps.nowinandroid.feature.licenses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.samples.apps.nowinandroid.core.network.model.LicenseItem
import com.google.samples.apps.nowinandroid.core.network.retrofit.JulesAdminApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LicenseViewModel @Inject constructor(
    private val api: JulesAdminApi
) : ViewModel() {

    private val _uiState = MutableStateFlow<LicenseUiState>(LicenseUiState.Loading)
    val uiState: StateFlow<LicenseUiState> = _uiState.asStateFlow()

    init { fetchLicenses() }

    fun fetchLicenses() {
        viewModelScope.launch {
            _uiState.value = LicenseUiState.Loading
            try {
                // Fetch using a dev key - replace in prod!
                val response = api.getLicenses("dev-admin-key") 
                _uiState.value = LicenseUiState.Success(response.licenses)
            } catch (e: Exception) {
                _uiState.value = LicenseUiState.Error(e.localizedMessage ?: "Unknown Error")
            }
        }
    }
}

sealed interface LicenseUiState {
    object Loading : LicenseUiState
    data class Success(val licenses: List<LicenseItem>) : LicenseUiState
    data class Error(val message: String) : LicenseUiState
}
