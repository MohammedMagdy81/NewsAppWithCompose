package com.example.newsappcompose.presentaion.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LongState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.newsappcompose.domin.model.Article

@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onClick: (Article) -> Unit
) {
    if (articles.isEmpty()){
        EmptyScreen()
    }
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(all = 16.dp)
    ) {
        items(
            count = articles.size,
        ) {
            articles[it]?.let { article ->
                ArticleCard(article = article, onArticleClick = { onClick(article) })
            }
        }
    }

}


@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onArticleClick: (Article) -> Unit
) {
    val handlePagingItems = HandlePagingResult(articles = articles)
    if (handlePagingItems) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(5.dp)
        ) {
            items(count = articles.itemCount) { index ->
                articles[index]?.let { article ->
                    ArticleCard(modifier = modifier, article = article, onArticleClick = {
                        onArticleClick(article)
                    })
                }

            }
        }
    }


}


@Composable
fun HandlePagingResult(
    articles: LazyPagingItems<Article>,
): Boolean {
    val loadState = articles.loadState

    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }
    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }
        error != null -> {
            EmptyScreen()
            false
        }
        else -> {
            true
        }
    }
}


@Composable
private fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.run { spacedBy(4.dp) }) {
        repeat(10) {
            ArticleShimmerEffect(
                modifier = Modifier.padding(horizontal = 5.dp)
            )
        }
    }
}











