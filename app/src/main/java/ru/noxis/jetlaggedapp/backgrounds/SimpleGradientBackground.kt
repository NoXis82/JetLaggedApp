package ru.noxis.jetlaggedapp.backgrounds

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import ru.noxis.jetlaggedapp.ui.theme.White
import ru.noxis.jetlaggedapp.ui.theme.Yellow
import ru.noxis.jetlaggedapp.ui.theme.YellowVariant

fun Modifier.simpleGradient(): Modifier =
    drawWithCache {
        val gradientBrush = Brush.verticalGradient(listOf(Yellow, YellowVariant, White))
        onDrawBehind {
            drawRect(gradientBrush, alpha = 1f)
        }
    }