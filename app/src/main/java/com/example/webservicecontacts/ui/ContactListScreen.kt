
package com.example.webservicecontacts.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.webservicecontacts.UsersUiState
import com.example.webservicecontacts.model.User
import com.example.webservicecontacts.network.RetrofitInstance

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactListScreen(
    onContactClick: (Int) -> Unit
) {
    var uiState by remember {
        mutableStateOf<UsersUiState>(UsersUiState.Loading)
    }

    LaunchedEffect(Unit) {
        uiState = try {
            val users = RetrofitInstance.api.getUsers()
            UsersUiState.Succcess(users)
        }  catch (e: Exception) {
        android.util.Log.e("ContactsAPI", "Failed to load contacts", e)
        UsersUiState.Error(e.toString())
    }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Contacts") }
            )
        }
    ) { paddingValues ->

        when (val state = uiState) {

            UsersUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is UsersUiState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Error: ${state.message}")
                }
            }

            is UsersUiState.Succcess -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    items(
                        items = state.users,
                        key = { it.id }
                    ) { user ->
                        ContactRow(
                            user = user,
                            onClick = {
                                onContactClick(user.id)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ContactRow(
    user: User,
    onClick: () -> Unit
) {
    androidx.compose.material3.ListItem(

        headlineContent = {
            Text(
                text = user.name,
                fontWeight = FontWeight.SemiBold
            )
        },
        supportingContent = {

            Text(user.email)
        },
        modifier = Modifier.clickable(onClick = onClick)
    )

    HorizontalDivider()
}
