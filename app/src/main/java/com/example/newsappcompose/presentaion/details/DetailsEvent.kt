package com.example.newsappcompose.presentaion.details

import com.example.newsappcompose.domin.model.Article

sealed class DetailsEvent {

    data class upsertDeleteArticle(val article: Article) :DetailsEvent()
    object removeSideEffect :DetailsEvent()
}
