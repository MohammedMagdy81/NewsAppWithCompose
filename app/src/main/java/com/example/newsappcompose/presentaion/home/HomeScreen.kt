package com.example.newsappcompose.presentaion.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.paging.compose.LazyPagingItems
import com.example.newsappcompose.domin.model.Article
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsappcompose.R
import com.example.newsappcompose.presentaion.common.ArticleList
import com.example.newsappcompose.presentaion.common.SearchBar
import com.example.newsappcompose.presentaion.navgraph.Route

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(articles: LazyPagingItems<Article>, navigation: (String) -> Unit) {
    val title by remember {
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items.slice(
                    IntRange(start = 0, endInclusive = 9)
                ).joinToString("\uD83d\uDFE5") { it.title!! }
            } else {
                ""
            }

        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 6.dp)
            .statusBarsPadding()
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "logo",
            modifier = Modifier
                .width(150.dp)
                .heightIn(30.dp)
                .padding(horizontal = 6.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        SearchBar(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "",
            readOnly = true,
            onValueChange = {},
            onClick = {
                navigation(Route.SearchScreen.route)
            },
            onSearch = {

            })

        Spacer(modifier = Modifier.height(22.dp))
        Text(
            text = title, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 22.dp)
                .basicMarquee(),
            fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder)
        )
        Spacer(modifier = Modifier.height(22.dp))
        ArticleList(
            modifier = Modifier.padding(horizontal = 20.dp),
            articles = articles,
            onArticleClick = {
                navigation(Route.DetailsScreen.route)
            })

    }
}



















