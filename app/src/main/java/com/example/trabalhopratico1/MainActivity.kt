package com.example.trabalhopratico1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.trabalhopratico1.ui.screens.ToDoScreen
import com.example.trabalhopratico1.ui.theme.TrabalhoPratico1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrabalhoPratico1Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ToDoScreen()
                }
            }
        }
    }
}
