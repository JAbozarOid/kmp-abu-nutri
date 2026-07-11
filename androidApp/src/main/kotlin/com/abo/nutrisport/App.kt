package com.abo.nutrisport

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.abo.nutrisport.navigation.SetupNavGraph

@Composable
@Preview
fun App() {
    MaterialTheme {
        SetupNavGraph()
    }
}