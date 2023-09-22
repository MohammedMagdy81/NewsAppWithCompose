package com.example.newsappcompose.presentaion.search

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsappcompose.presentaion.common.ArticleList
import com.example.newsappcompose.presentaion.common.SearchBar
import com.example.newsappcompose.presentaion.navgraph.Route

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvents) -> Unit,
    navigate: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { event(SearchEvents.UpdateSearchQuery(it)) },
            onSearch = { event(SearchEvents.SearchNews) }
        )
        Spacer(modifier = Modifier.height(20.dp))
        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticleList(
                articles = articles,
                onArticleClick = { navigate(Route.DetailsScreen.route) })

        }
    }
}












