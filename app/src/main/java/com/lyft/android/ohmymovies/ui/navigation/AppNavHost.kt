package com.lyft.android.ohmymovies.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lyft.android.ohmymovies.ui.home.MoviesHomeScreen
import com.lyft.android.ohmymovies.ui.section.SectionScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.home
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier.fillMaxSize()
    ) {
        composable(
            route = Routes.home
        ) {
            MoviesHomeScreen(openSection = {
                navController.navigate(Routes.section)
            })
        }
        composable(
            route = Routes.section
        ) {
            SectionScreen()
        }
    }
}