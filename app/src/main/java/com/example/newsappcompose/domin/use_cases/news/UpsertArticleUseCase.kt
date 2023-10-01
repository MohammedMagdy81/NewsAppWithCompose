package com.example.newsappcompose.domin.use_cases.news

import com.example.newsappcompose.domin.model.Article
import com.example.newsappcompose.domin.repository.NewsRepository

class UpsertArticleUseCase(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(article: Article) =
        newsRepository.upsertArticle(article)
}