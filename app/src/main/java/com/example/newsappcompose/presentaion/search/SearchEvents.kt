package com.example.newsappcompose.presentaion.search

sealed class SearchEvents {

    data class UpdateSearchQuery(val searchQuery: String) : SearchEvents()

    object SearchNews : SearchEvents()
}
