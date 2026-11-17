package com.example.webservicecontacts
import com.example.webservicecontacts.model.User

sealed interface UsersUiState {

    data object Loading : UsersUiState

    data class Succcess(
        val users: List<User>
    ) : UsersUiState

    data class Error(
        val message: String
    ) : UsersUiState
}
