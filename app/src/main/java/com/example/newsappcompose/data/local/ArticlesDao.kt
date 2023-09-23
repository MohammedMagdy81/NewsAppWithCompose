package com.example.newsappcompose.data.local

import androidx.room.*
import com.example.newsappcompose.domin.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticlesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertArticle(article: Article)

    @Delete
    suspend fun deleteArticle(article: Article)

    @Query("SELECT * FROM article")
    fun getAllArticles(): Flow<List<Article>>
}