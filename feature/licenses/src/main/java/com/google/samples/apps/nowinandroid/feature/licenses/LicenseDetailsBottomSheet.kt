package com.google.samples.apps.nowinandroid.feature.licenses

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LicenseDetailsBottomSheet(
    owner: String,
    isActive: Boolean,
    onDismissRequest: () -> Unit,
    onToggleActive: () -> Unit,
    onExtend: () -> Unit,
    onResetHwid: () -> Unit,
    onEditOwner: () -> Unit,
    onDelete: () -> Unit
) {
    ModalBottomSheet(onDismissRequest = onDismissRequest) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp, top = 8.dp, start = 16.dp, end = 16.dp)
        ) {
            Text(
                text = "Manage: $owner",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Action Buttons
            ActionButton(
                icon = if (isActive) Icons.Filled.Close else Icons.Filled.Check,
                text = if (isActive) "Deactivate License" else "Activate License",
                onClick = onToggleActive
            )
            
            ActionButton(
                icon = Icons.Filled.DateRange,
                text = "Extend Duration",
                onClick = onExtend
            )
            
            ActionButton(
                icon = Icons.Filled.Refresh,
                text = "Reset HWID Lock",
                onClick = onResetHwid
            )

            ActionButton(
                icon = Icons.Filled.Edit,
                text = "Edit Owner Name",
                onClick = onEditOwner
            )

            Spacer(modifier = Modifier.height(16.dp))
            Divider()
            Spacer(modifier = Modifier.height(16.dp))

            // Delete Button (Danger)
            Button(
                onClick = onDelete,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Filled.Delete, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Delete License")
            }
        }
    }
}

@Composable
private fun ActionButton(icon: ImageVector, text: String, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.onSurface)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text, color = MaterialTheme.colorScheme.onSurface, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
