package com.vvchn.vktestcase.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.vvchn.vktestcase.presentation.navigation.PokemonsNavHost
import com.vvchn.vktestcase.presentation.ui.theme.VKTestCaseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VKTestCaseTheme {
                PokemonsNavHost()
            }
        }
    }
}