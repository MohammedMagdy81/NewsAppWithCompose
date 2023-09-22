package com.example.newsappcompose.presentaion.details.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsappcompose.domin.model.Article
import com.example.newsappcompose.presentaion.details.DetailsEvent


@Composable
fun DetailsScreen(
    article: Article,
    event: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopBar(
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, article.url)
                    it.type = "text/plain"
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBackBtnClick = navigateUp,
            onBookmarkClick = { event(DetailsEvent.SaveArticleEvent) },
            onBrowseClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(article.url)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            }
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = 20.dp,
                end = 20.dp,
                top = 20.dp
            )
        ) {
            item {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.3f)
                        .clip(MaterialTheme.shapes.medium),
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(article.urlToImage)
                        .build(),
                    contentDescription = "Article image",
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = article.title.toString(),
                    style = MaterialTheme.typography.labelMedium,
                    color = colorResource(id = com.example.newsappcompose.R.color.text_title)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = article.content.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(id = com.example.newsappcompose.R.color.body)
                )
            }
        }
    }
}











