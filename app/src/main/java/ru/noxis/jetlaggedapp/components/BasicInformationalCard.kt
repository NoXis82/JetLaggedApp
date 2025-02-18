package ru.noxis.jetlaggedapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.noxis.jetlaggedapp.ui.theme.JetLaggedAppTheme

@Composable
fun BasicInformationalCard(
    modifier: Modifier = Modifier,
    borderColor: Color,
    content: @Composable () -> Unit
) {
    val shape = RoundedCornerShape(24.dp)
    Card(
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = JetLaggedAppTheme.extraColors.cardBackground
        ),
        modifier = modifier
            .padding(8.dp),
        border = BorderStroke(2.dp, borderColor)
    ) {
        Box {
            content()
        }
    }
}