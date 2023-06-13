package com.tws.stepontime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.tws.stepontime.ui.commons.nav_graph.AppNavHost
import com.tws.stepontime.ui.theme.StepOnTimeComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreen = installSplashScreen()
        val isSplashScreen = mutableStateOf(true)

        lifecycleScope.launch(Dispatchers.Default) {
            // same as that of Global scope
            delay(3000L)
            isSplashScreen.value = false
        }

        splashScreen.setKeepOnScreenCondition {
            //splash will visible until true, hides if it turns to false
            isSplashScreen.value
        }

        setContent {
            StepOnTimeComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost(startDestination = "Auth_Graph")
                }
            }
        }
    }
}