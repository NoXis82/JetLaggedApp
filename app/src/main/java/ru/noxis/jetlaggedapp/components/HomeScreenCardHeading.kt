package ru.noxis.jetlaggedapp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.noxis.jetlaggedapp.ui.theme.HeadingStyle

@Composable
fun HomeScreenCardHeading(text: String) {
    Text(
        text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        textAlign = TextAlign.Center,
        style = HeadingStyle
    )
}