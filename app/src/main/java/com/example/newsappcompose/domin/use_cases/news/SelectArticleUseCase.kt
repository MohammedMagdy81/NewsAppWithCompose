package com.example.newsappcompose.domin.use_cases.news

import com.example.newsappcompose.data.local.ArticlesDao
import com.example.newsappcompose.domin.model.Article
import com.example.newsappcompose.domin.repository.NewsRepository

class SelectArticleUseCase(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(url: String): Article? =
        newsRepository.getArticle(url)


}