package com.example.newsappcompose.domin.use_cases.news

import com.example.newsappcompose.data.local.ArticlesDao
import com.example.newsappcompose.domin.model.Article

class SelectArticlesUseCase(
    private val newsDao: ArticlesDao
) {
     operator fun invoke() = newsDao.getAllArticles()


}