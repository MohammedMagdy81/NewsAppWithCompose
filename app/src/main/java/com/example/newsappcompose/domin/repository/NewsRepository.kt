package com.example.newsappcompose.domin.repository

import androidx.paging.PagingData
import com.example.newsappcompose.domin.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

     fun getNews(sources:List<String>):Flow<PagingData<Article>>
}