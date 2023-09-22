package com.example.newsappcompose.presentaion.common

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsappcompose.R
import com.example.newsappcompose.ui.theme.NewsAppComposeTheme

@Composable
fun Modifier.ShimmerEffect() = composed {
    val transition = rememberInfiniteTransition()
    val alpha = transition.animateFloat(
        initialValue = .2f,
        targetValue = .9f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        )
    ).value
    background(color = colorResource(id = R.color.shimmer)).alpha(alpha)
}

@Composable
fun ArticleShimmerEffect(
    modifier: Modifier =Modifier
){
    Row {
        Box(
            modifier = modifier
                .size(96.dp)
                .clip(MaterialTheme.shapes.medium)
                .ShimmerEffect(),
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .height(96.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .padding(horizontal = 6.dp)
                    .ShimmerEffect(),
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = modifier
                        .fillMaxWidth(.5f)
                        .height(15.dp)
                        .padding(horizontal = 6.dp)
                        .ShimmerEffect(),
                )

            }

        }
    }
}
@Composable
@Preview(showSystemUi = true, showBackground = true)
fun PreviewArticleShimmerEffect(){
    NewsAppComposeTheme {
        ArticleShimmerEffect()
    }

}