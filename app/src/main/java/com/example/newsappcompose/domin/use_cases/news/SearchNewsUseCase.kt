package com.example.newsappcompose.domin.use_cases.news

import com.example.newsappcompose.domin.repository.NewsRepository

class SearchNewsUseCase(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(searchQuery: String, sources: List<String>) =
        newsRepository.searchNews(searchQuery = searchQuery, sources = sources)
}

