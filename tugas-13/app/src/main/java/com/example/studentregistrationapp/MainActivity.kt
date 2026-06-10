package com.example.studentregistrationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.studentregistrationapp.data.AppDatabase
import com.example.studentregistrationapp.ui.MainScreen
import com.example.studentregistrationapp.ui.theme.StudentRegistrationAppTheme
import com.example.studentregistrationapp.viewmodel.StudentViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val dao = AppDatabase
            .getDatabase(applicationContext)
            .siswaDao()

        enableEdgeToEdge()
        setContent {
            StudentRegistrationAppTheme {
                val viewModel = StudentViewModel(dao)
                MainScreen(viewModel)
            }
        }
    }
}
