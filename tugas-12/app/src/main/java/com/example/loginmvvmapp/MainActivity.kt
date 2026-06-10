package com.example.loginmvvmapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loginmvvmapp.data.local.database.AppDatabase
import com.example.loginmvvmapp.data.repository.UserRepository
import com.example.loginmvvmapp.ui.screen.LoginScreen
import com.example.loginmvvmapp.ui.theme.LoginMVVMAppTheme
import com.example.loginmvvmapp.viewmodel.LoginViewModel
import com.example.loginmvvmapp.viewmodel.LoginViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(this)
        val repository = UserRepository(database.userDao())
        val factory = LoginViewModelFactory(repository)
        
        enableEdgeToEdge()
        setContent {
            LoginMVVMAppTheme {
                val viewModel: LoginViewModel = viewModel(factory = factory)

                LaunchedEffect(Unit) {
                    viewModel.insertDummyUser()
                }
                
                LoginScreen(viewModel)
            }
        }
    }
}
