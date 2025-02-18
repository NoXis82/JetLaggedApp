package ru.noxis.jetlaggedapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

data class JetLaggedExtraColors(
    val header: Color = Color.Unspecified,
    val cardBackground: Color = Color.Unspecified,
    val bed: Color = Color.Unspecified,
    val sleep: Color = Color.Unspecified,
    val wellness: Color = Color.Unspecified,
    val heart: Color = Color.Unspecified,
    val heartWave: List<Color> = listOf(Color.Unspecified),
    val heartWaveBackground: Color = Color.Unspecified,
    val sleepChartPrimary: Color = Color.Unspecified,
    val sleepChartSecondary: Color = Color.Unspecified,
    val sleepAwake: Color = Color.Unspecified,
    val sleepRem: Color = Color.Unspecified,
    val sleepLight: Color = Color.Unspecified,
    val sleepDeep: Color = Color.Unspecified,
)

val LocalExtraColors = staticCompositionLocalOf {
    JetLaggedExtraColors()
}

private val LightExtraColors = JetLaggedExtraColors(
    header = Yellow,
    cardBackground = White,
    bed = Lilac,
    sleep = MintGreen,
    wellness = LightBlue,
    heart = Coral,
    heartWave = listOf(Pink, Purple, Green),
    heartWaveBackground = Coral.copy(alpha = 0.2f),
    sleepChartPrimary = Yellow,
    sleepChartSecondary = YellowVariant,
    sleepAwake = SleepAwake,
    sleepRem = SleepRem,
    sleepLight = SleepLight,
    sleepDeep = SleepDeep,
)

private val DarkExtraColors = JetLaggedExtraColors(
    header = Red,
    cardBackground = Black,
    bed = DarkLilac,
    sleep = DarkMintGreen,
    wellness = DarkBlue,
    heart = DarkCoral,
    heartWave = listOf(DarkPink, DarkPurple, DarkGreen),
    heartWaveBackground = DarkCoral.copy(alpha = 0.4f),
    sleepChartPrimary = Red,
    sleepChartSecondary = RedVariant,
    sleepAwake = SleepAwakeDark,
    sleepRem = SleepRemDark,
    sleepLight = SleepLightDark,
    sleepDeep = SleepDeepDark,
)

@Composable
fun JetLaggedAppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme: ColorScheme
    val extraColors: JetLaggedExtraColors
    if (isDarkTheme) {
        colorScheme = DarkColorScheme
        extraColors = DarkExtraColors
    } else {
        colorScheme = LightColorScheme
        extraColors = LightExtraColors
    }

    CompositionLocalProvider(LocalExtraColors provides extraColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}

object JetLaggedAppTheme {
    val extraColors: JetLaggedExtraColors
        @Composable
        get() = LocalExtraColors.current
}
