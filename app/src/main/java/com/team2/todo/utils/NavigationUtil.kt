package com.team2.todo.utils

/**
 * Created by Manu KJ on 11/1/23.
 */

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.team2.todo.screens.completed_listing.CompletedListing
import com.team2.todo.screens.listing.Listing

// Enum of all the Screen
enum class Screen {
    Listing, CompletedListing,
}

object NavigationUtil {
    lateinit var navController: NavHostController

    // initializing the nav controller before using it
    fun init(navController: NavHostController) {
        this.navController = navController;
    }

    // nagivate to the given Enum
    fun navigateTo(screen: Screen) {
        navController.navigate(screen.name)
    }
}


@Composable
fun NavHostControllerProvider() {
    NavHost(navController = NavigationUtil.navController, startDestination = Screen.Listing.name) {
        composable(Screen.Listing.name) { Listing() }
        composable(Screen.CompletedListing.name) { CompletedListing() }

    }
}