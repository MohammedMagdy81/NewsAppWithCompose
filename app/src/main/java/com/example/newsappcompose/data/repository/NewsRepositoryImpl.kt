package com.example.newsappcompose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsappcompose.data.remote.NewsApi
import com.example.newsappcompose.data.remote.NewsPagingSource
import com.example.newsappcompose.domin.model.Article
import com.example.newsappcompose.domin.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class NewsRepositoryImpl(
    private val newsApi: NewsApi
) : NewsRepository {

    override  fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(newsApi = newsApi, sources = sources.joinToString(separator = ","))
            }
        ).flow

    }
}