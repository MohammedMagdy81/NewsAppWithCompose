package com.example.newsappcompose.presentaion.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsappcompose.domin.use_cases.news.NewsUseCases
import com.example.newsappcompose.domin.use_cases.news.SearchNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsUseCase: NewsUseCases
) : ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    fun onEvent(event: SearchEvents) {
        when (event) {
            is SearchEvents.SearchNews -> {
                searchNews()
            }
            is SearchEvents.UpdateSearchQuery -> {
                _state.value = state.value.copy(searchQuery = event.searchQuery)
            }
        }
    }

    private fun searchNews() {
        val articles = newsUseCase.searchNewsUseCase.invoke(
            searchQuery = state.value.searchQuery ,
            sources = listOf("bbc-news", "abc-news", "al-jazeera-english")
        ).cachedIn(viewModelScope)

        _state.value = state.value.copy(articles = articles)
    }
}










