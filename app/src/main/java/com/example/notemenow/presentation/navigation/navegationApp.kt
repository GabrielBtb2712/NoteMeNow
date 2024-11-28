package com.example.notemenow.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notemenow.presentation.viewModel.NoteViewModel
import com.example.notemenow.presentation.views.AddEditNoteScreen
import com.example.notemenow.presentation.views.Home


@Composable
fun AppNavigation(viewModel: NoteViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Home(
                navController = navController,
                viewModel = viewModel
                )
        }
        composable("addNote") {
            AddEditNoteScreen(
                navController = navController,
                viewModel = viewModel

            )
        }
//        composable(
//            "addEditNote/{noteId}",
//            arguments = listOf(navArgument("noteId"){ type = NavType.IntType })
//        ) {
//            backStackEntry ->
//            val noteId =backStackEntry.arguments?.getInt("noteId") ?: -1
//            AddEditNoteScreen(
//                navController = navController,
//                viewModel = viewModel,
//                noteId = noteId
//            )
//        }

    }
}