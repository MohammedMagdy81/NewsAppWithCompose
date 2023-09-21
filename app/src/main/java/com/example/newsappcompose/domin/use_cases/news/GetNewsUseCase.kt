package com.example.newsappcompose.domin.use_cases.news

import androidx.paging.PagingData
import com.example.newsappcompose.domin.model.Article
import com.example.newsappcompose.domin.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNewsUseCase(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(sources: List<String>): Flow<PagingData<Article>> =
        newsRepository.getNews(sources)
}