package com.example.newsappcompose.presentaion.onBoarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.newsappcompose.utils.Dimens.MEDIUM_PADDING1
import com.example.newsappcompose.utils.Dimens.MEDIUM_PADDING2
import com.example.newsappcompose.presentaion.onBoarding.Page
import com.example.newsappcompose.presentaion.onBoarding.pages
import com.example.newsappcompose.ui.theme.NewsAppComposeTheme

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page
) {
    Column(modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.6f),
            painter = painterResource(id = page.image),
            contentDescription = "boarding",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(MEDIUM_PADDING1))
        Text(
            text = page.title,
            modifier = Modifier.padding(horizontal = MEDIUM_PADDING2),
            style = androidx.compose.material3.MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = com.example.newsappcompose.R.color.display_small)
        )
        Spacer(modifier = Modifier.height(MEDIUM_PADDING1))
        Text(
            text = page.description,
            modifier = Modifier.padding(horizontal = MEDIUM_PADDING2),
            style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
            color = Color.LightGray
        )
    }


}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun PreviewPage() {
    NewsAppComposeTheme {
        OnBoardingPage(page = pages[0])
    }
}














