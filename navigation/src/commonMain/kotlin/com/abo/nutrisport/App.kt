package com.abo.nutrisport

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.abo.nutrisport.Constants.WEB_CLIENT_ID
import com.abo.nutrisport.data.domain.CustomerRepository
import com.abo.nutrisport.navigation.Screen
import com.abo.nutrisport.navigation.SetupNavGraph
import com.mmk.kmpauth.google.GoogleAuthCredentials
import com.mmk.kmpauth.google.GoogleAuthProvider
import org.koin.compose.koinInject

@Composable
fun App() {
    MaterialTheme {
        val customerRepository = koinInject<CustomerRepository>()
        val isUserAuthenticated = remember {customerRepository.getCurrentUserId() != null}
        var appReady by remember { mutableStateOf(false) }
        val startDestination  = remember {
            if (isUserAuthenticated) Screen.HomeGraph else Screen.Auth
        }
        LaunchedEffect(Unit) {
            GoogleAuthProvider.create(credentials = GoogleAuthCredentials(serverId = WEB_CLIENT_ID))
            appReady = true
        }
        AnimatedVisibility(
            appReady,
            modifier = Modifier.fillMaxSize(),
        ) {
            SetupNavGraph(
                startDestination = startDestination
            )
        }
    }
}
