package com.example.notemenow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.notemenow.presentation.navigation.AppNavigation
import com.example.notemenow.presentation.viewModel.NoteViewModel
import com.example.notemenow.ui.theme.NoteMeNowTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val noteViewModel: NoteViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteMeNowTheme {
                AppNavigation(viewModel = noteViewModel)
            }
        }
    }
}

