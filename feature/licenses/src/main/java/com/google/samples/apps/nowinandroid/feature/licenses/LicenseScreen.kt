package com.google.samples.apps.nowinandroid.feature.licenses

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LicenseScreen(
    viewModel: LicenseViewModel = hiltViewModel()
) {
    var searchQuery by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Licenses") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: Open Create Sheet */ },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Create License")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                placeholder = { Text("Search by owner or key...") },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
                shape = MaterialTheme.shapes.large,
                singleLine = true
            )

            // State handling
            when (uiState) {
                is LicenseUiState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                is LicenseUiState.Error -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                        Text("Error: ${(uiState as LicenseUiState.Error).message}", color = MaterialTheme.colorScheme.error)
                    }
                }
                is LicenseUiState.Success -> {
                    val licenses = (uiState as LicenseUiState.Success).licenses
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 80.dp) // padding for FAB
                    ) {
                        items(licenses) { license ->
                            LicenseCard(
                                owner = license.owner,
                                licenseKey = license.key,
                                isActive = license.isActive,
                                type = license.type,
                                expiresAt = license.expiresAt ?: "Never",
                                onClick = { /* TODO: Open Details Sheet */ }
                            )
                        }
                    }
                }
            }
        }
    }
}
