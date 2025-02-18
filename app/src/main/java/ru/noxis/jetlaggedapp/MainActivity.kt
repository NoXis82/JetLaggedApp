package ru.noxis.jetlaggedapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.noxis.jetlaggedapp.heartrate.HeartRateCard
import ru.noxis.jetlaggedapp.ui.theme.JetLaggedAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetLaggedAppTheme {
                val windowSizeClass = calculateWindowSizeClass(this)
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    JetLaggedScreen(
                        modifier = Modifier.padding(innerPadding),
                        windowSizeClass = windowSizeClass.widthSizeClass
                    )
                }
            }
        }
    }
}
