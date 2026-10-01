package com.google.samples.apps.nowinandroid.feature.licenses

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * A beautiful, clean card representing a single License.
 * Designed to be immediately understandable at a glance.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LicenseCard(
    owner: String,
    licenseKey: String,
    isActive: Boolean,
    type: String,
    expiresAt: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f)
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Header: Status indicator and Owner
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Status Dot
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(if (isActive) Color(0xFF4CAF50) else Color(0xFFE53935))
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = owner,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.weight(1f))
                // License Type Badge
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Text(
                        text = type.uppercase(),
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // License Key (Masked for clean look)
            val maskedKey = if (licenseKey.length > 8) {
                "${licenseKey.take(8)}••••••••••••••••"
            } else {
                licenseKey
            }
            Text(
                text = "Key: $maskedKey",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Expiry Info
            if (expiresAt != null) {
                Text(
                    text = "Expires: $expiresAt",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                Text(
                    text = "Never Expires",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview
@Composable
private fun LicenseCardPreview() {
    MaterialTheme {
        Column {
            LicenseCard(
                owner = "John Doe",
                licenseKey = "ab12cd34-5678-90ef-gh12",
                isActive = true,
                type = "Standard",
                expiresAt = "Oct 24, 2026",
                onClick = {}
            )
            LicenseCard(
                owner = "Jane Smith",
                licenseKey = "timer-x987-6543-210z",
                isActive = false,
                type = "Timer",
                expiresAt = "Expired",
                onClick = {}
            )
        }
    }
}
