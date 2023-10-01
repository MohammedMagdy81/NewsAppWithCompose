package com.example.newsappcompose.domin.use_cases.news

import com.example.newsappcompose.data.local.ArticlesDao
import com.example.newsappcompose.domin.model.Article
import com.example.newsappcompose.domin.repository.NewsRepository

class SelectArticlesUseCase(
    private val newsRepository: NewsRepository
) {
     operator fun invoke() = newsRepository.getArticles()


}