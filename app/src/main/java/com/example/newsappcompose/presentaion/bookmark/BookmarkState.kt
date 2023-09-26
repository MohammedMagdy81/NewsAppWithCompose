package com.example.newsappcompose.presentaion.bookmark

import com.example.newsappcompose.domin.model.Article

data class BookmarkState(

    val articles: List<Article> = emptyList()

)
