package com.example.newsappcompose.presentaion.newsnavigator

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsappcompose.R
import com.example.newsappcompose.domin.model.Article
import com.example.newsappcompose.presentaion.bookmark.BookmarkScreen
import com.example.newsappcompose.presentaion.bookmark.BookmarkViewModel
import com.example.newsappcompose.presentaion.details.DetailsEvent
import com.example.newsappcompose.presentaion.details.DetailsViewModel
import com.example.newsappcompose.presentaion.details.components.DetailsScreen
import com.example.newsappcompose.presentaion.home.HomeScreen
import com.example.newsappcompose.presentaion.home.HomeViewModel
import com.example.newsappcompose.presentaion.navgraph.Route
import com.example.newsappcompose.presentaion.newsnavigator.components.BottomNavigationItem
import com.example.newsappcompose.presentaion.newsnavigator.components.NewsBottomNavigation
import com.example.newsappcompose.presentaion.search.SearchScreen
import com.example.newsappcompose.presentaion.search.SearchViewModel
import com.example.newsappcompose.utils.Constants.ARTICLE_KEY
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories

@Composable
fun NewsNavigator() {

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark")
        )
    }

    val navController = rememberNavController()
    val backstackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable { mutableStateOf(0) }


    selectedItem = remember(key1 = backstackState) {
        when (backstackState?.destination?.route) {
            Route.HomeScreen.route -> 0
            Route.SearchScreen.route -> 1
            Route.BookmarkScreen.route -> 2

            else -> 0
        }
    }

    val isBottomVisible = remember(key1 = backstackState) {
        backstackState?.destination?.route == Route.HomeScreen.route ||
                backstackState?.destination?.route == Route.SearchScreen.route ||
                backstackState?.destination?.route == Route.BookmarkScreen.route
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomVisible) {
                NewsBottomNavigation(
                    items = bottomNavigationItems,
                    selected = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> {
                                navigateToTab(navController, Route.HomeScreen.route)
                            }
                            1 -> {
                                navigateToTab(navController, Route.SearchScreen.route)
                            }
                            2 -> {
                                navigateToTab(navController, Route.BookmarkScreen.route)
                            }
                        }
                    }
                )
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articles = articles,
                    navigateToSearch = { navigateToTab(navController, Route.SearchScreen.route) },
                    navigateToDetails = { article ->
                        navigateToDetailsScreen(navController, article)
                    }
                )
            }

            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetails = { navigateToDetailsScreen(navController, it) }
                )
            }

            composable(route = Route.DetailsScreen.route) {
                val viewModel: DetailsViewModel = hiltViewModel()

                if (viewModel.sideEffect != null) {
                    Toast.makeText(LocalContext.current, viewModel.sideEffect, Toast.LENGTH_SHORT)
                        .show()
                }
                viewModel.onEvent(DetailsEvent.removeSideEffect)

                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>(ARTICLE_KEY)
                    ?.let {
                        DetailsScreen(
                            article = it,
                            event = viewModel::onEvent,
                            navigateUp = { navController.navigateUp() })
                    }
            }

            composable(route = Route.BookmarkScreen.route) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                BookmarkScreen(
                    state = state,
                    navigateToDetails = { navigateToDetailsScreen(navController, it) }
                )
            }
        }

    }
}

private fun navigateToDetailsScreen(navController: NavHostController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set(ARTICLE_KEY, article)
    navController.navigate(route = Route.DetailsScreen.route)
}


private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}









