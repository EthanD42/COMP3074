package com.example.comp3074_lab5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.comp3074_lab5.data.AppDatabase
import com.example.comp3074_lab5.ui.CustomerApp
import com.example.comp3074_lab5.ui.CustomerViewModel
import com.example.comp3074_lab5.ui.CustomerViewModelFactory
import com.example.comp3074_lab5.ui.theme.COMP3074_Lab5Theme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = AppDatabase.getDatabase(applicationContext)

        setContent {
            COMP3074_Lab5Theme {
                MaterialTheme {
                    val viewModel: CustomerViewModel = viewModel(
                        factory = CustomerViewModelFactory(
                            database.customerDao()
                        )
                    )

                    CustomerApp(viewModel)
                }
            }
        }
    }
}