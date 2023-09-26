package com.example.newsappcompose.presentaion.navgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsappcompose.presentaion.home.HomeScreen
import com.example.newsappcompose.presentaion.home.HomeViewModel
import com.example.newsappcompose.presentaion.newsnavigator.NewsNavigator
import com.example.newsappcompose.presentaion.onBoarding.OnBoardingScreen
import com.example.newsappcompose.presentaion.onBoarding.OnBoardingViewModel
import com.example.newsappcompose.presentaion.search.SearchScreen
import com.example.newsappcompose.presentaion.search.SearchViewModel

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNav.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(route = Route.OnBoardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(onEvent = viewModel::onEvent)
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {
            composable(
                route = Route.NewsNavigatorScreen.route
            ) {
                    NewsNavigator()
            }
        }

    }
}














