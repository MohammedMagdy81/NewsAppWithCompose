package com.example.newsappcompose.domin.use_cases.news

import com.example.newsappcompose.data.local.ArticlesDao
import com.example.newsappcompose.domin.model.Article

class DeleteArticleUseCase(
    private val newsDao: ArticlesDao
) {
    suspend operator fun invoke(article: Article) = newsDao.deleteArticle(article)

}