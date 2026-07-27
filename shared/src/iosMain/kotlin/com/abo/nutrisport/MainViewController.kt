package com.abo.nutrisport

import androidx.compose.ui.window.ComposeUIViewController
import com.abo.nutrisport.di.initKoin


fun MainViewController() = ComposeUIViewController(configure = {initKoin()}) { App() }