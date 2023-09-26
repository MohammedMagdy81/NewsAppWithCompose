package com.example.newsappcompose.presentaion.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappcompose.domin.model.Article
import com.example.newsappcompose.domin.use_cases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    var sideEffect by mutableStateOf<String?>(null)

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.removeSideEffect -> {
                sideEffect = null
            }
            is DetailsEvent.upsertDeleteArticle -> {
                viewModelScope.launch {
                    val article = newsUseCases.selectArticleUseCase.invoke(event.article.url)
                    if (article == null) {
                        upsertArticle(event.article)
                    } else {
                        deleteArticle(event.article)
                    }
                }
            }
        }
    }

    private suspend fun deleteArticle(article: Article) {
        newsUseCases.deleteArticleUseCase.invoke(article)
        sideEffect = "Article deleted"
    }

    private suspend fun upsertArticle(article: Article) {
        newsUseCases.upsertArticleUseCase.invoke(article)
        sideEffect = "Article inserted"
    }
}