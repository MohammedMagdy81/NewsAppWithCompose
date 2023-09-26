package com.example.newsappcompose.domin.use_cases.news

import com.example.newsappcompose.data.local.ArticlesDao
import com.example.newsappcompose.domin.model.Article

class SelectArticleUseCase(
    private val newsDao: ArticlesDao
) {
    suspend operator fun invoke(url: String): Article? =
        newsDao.getArticle(url)


}