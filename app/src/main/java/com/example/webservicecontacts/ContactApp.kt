
package com.example.webservicecontacts

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.webservicecontacts.ui.ContactListScreen
import com.example.webservicecontacts.ui.ContactDetailsScreen

@Composable
fun ContactApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "contacts"
    ) {
        composable("contacts") {
            ContactListScreen(
                onContactClick = { userId ->
                    navController.navigate("details/$userId")
                }
            )
        }

        composable(
            route = "details/{userId}",
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val userId = backStackEntry.arguments?.getInt("userId")

            ContactDetailsScreen(
                userId = userId,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
