package com.example.newsappcompose.domin.use_cases.news

import com.example.newsappcompose.data.local.ArticlesDao
import com.example.newsappcompose.domin.model.Article

class UpsertArticleUseCase(
    private val newsDao: ArticlesDao
) {
    suspend operator fun invoke(article: Article) =
        newsDao.upsertArticle(article)
}