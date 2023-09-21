package com.example.newsappcompose.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsappcompose.domin.model.Article
import com.example.newsappcompose.utils.Constants.API_KEY

class NewsPagingSource(
    private val newsApi: NewsApi,
    private val sources: String

) : PagingSource<Int, Article>() {

    private var newsTotalCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1 // get current page of articles that show to user
        return try {
            val newsResponse = newsApi.getNews(page = page, sources = sources, apiKey = API_KEY)
            newsTotalCount += newsResponse.articles.size
            val articles = newsResponse.articles.distinctBy { it.title }
            LoadResult.Page(
                data = articles,
                nextKey = if (newsTotalCount == newsResponse.totalResults) null else page + 1,
                prevKey = null
            )

        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return null
    }
}