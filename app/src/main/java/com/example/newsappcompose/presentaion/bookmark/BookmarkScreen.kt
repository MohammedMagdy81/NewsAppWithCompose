package com.example.newsappcompose.presentaion.bookmark

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.newsappcompose.R
import com.example.newsappcompose.domin.model.Article
import com.example.newsappcompose.presentaion.common.ArticleList
import com.example.newsappcompose.presentaion.common.ArticlesList


@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigateToDetails: (Article) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
    ) {

        Text(
            text = "Bookmark",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(
                id = R.color.text_title
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        ArticlesList(
            articles = state.articles,
            onClick = navigateToDetails
        )
    }
}