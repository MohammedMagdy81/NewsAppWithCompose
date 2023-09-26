package com.example.newsappcompose.presentaion.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsappcompose.R
import com.example.newsappcompose.domin.model.Article

@Composable
fun ArticleCard(
    modifier: Modifier =Modifier,
    article: Article,
    onArticleClick: () -> Unit
) {
    Row(modifier = modifier.clickable { onArticleClick() }) {
        AsyncImage(
            modifier = modifier
                .size(96.dp)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context = LocalContext.current).data(article.urlToImage)
                .build(),
            contentDescription = "Article image",
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 6.dp)
                .height(96.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = article.title.toString(),
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id = R.color.text_title),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = article.source?.name.toString(),
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = null,
                    modifier = Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = article.publishedAt.toString().substring(0,10),
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body)
                )

            }

        }
    }
}








