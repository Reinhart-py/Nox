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

data class DummyLicense(val id: String, val owner: String, val key: String, val isActive: Boolean, val type: String, val expiresAt: String?)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LicenseScreen() {
    var searchQuery by remember { mutableStateOf("") }
    
    // Dummy data for visualization before we hook up the API
    val licenses = listOf(
        DummyLicense("1", "SRJ Feb", "srj-1234-abcd", true, "Standard", "2026-12-31"),
        DummyLicense("2", "Test User", "test-0000-xxxx", false, "Timer", "Expired"),
        DummyLicense("3", "Admin Demo", "adm-5555-yyyy", true, "Standard", null)
    )

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

            // License List
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
                        expiresAt = license.expiresAt,
                        onClick = { /* TODO: Open Details Sheet */ }
                    )
                }
            }
        }
    }
}
