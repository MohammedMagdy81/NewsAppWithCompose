package com.example.newsappcompose.di

import android.app.Application
import androidx.room.Room
import com.example.newsappcompose.data.local.ArticlesDao
import com.example.newsappcompose.data.local.NewsDatabase
import com.example.newsappcompose.data.local.NewsTypeConvertor
import com.example.newsappcompose.data.manager.LocalUserManagerImpl
import com.example.newsappcompose.data.remote.NewsApi
import com.example.newsappcompose.data.repository.NewsRepositoryImpl
import com.example.newsappcompose.domin.manager.LocalUserManager
import com.example.newsappcompose.domin.repository.NewsRepository
import com.example.newsappcompose.domin.use_cases.appentry.AppEntryUseCases
import com.example.newsappcompose.domin.use_cases.appentry.ReadAppEntry
import com.example.newsappcompose.domin.use_cases.appentry.SaveAppEntry
import com.example.newsappcompose.domin.use_cases.news.*
import com.example.newsappcompose.utils.Constants.BASE_URL
import com.example.newsappcompose.utils.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserLocalManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManager: LocalUserManager): AppEntryUseCases =
        AppEntryUseCases(
            saveAppEntry = SaveAppEntry(localUserManager),
            readAppEntry = ReadAppEntry(localUserManager)
        )

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi): NewsRepository = NewsRepositoryImpl(
        newsApi = newsApi
    )

    @Provides
    @Singleton
    fun provideNewsApi(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)

    @Provides
    @Singleton
    fun provideRetrofit() = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideNewsDatabase(application: Application): NewsDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            NewsDatabase::class.java,
            NEWS_DATABASE_NAME
        )
            .addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun newsDao(newsDatabase: NewsDatabase): ArticlesDao = newsDatabase.newsDao()

    @Provides
    @Singleton
    fun provideNewsUseCase(
        newsRepository: NewsRepository,
        newsDao: ArticlesDao
    ) = NewsUseCases(
        getNewsUseCase = GetNewsUseCase(newsRepository),
        searchNewsUseCase = SearchNewsUseCase(newsRepository),
        upsertArticleUseCase = UpsertArticleUseCase(newsDao),
        selectArticleUseCase = SelectArticleUseCase(newsDao),
        deleteArticleUseCase = DeleteArticleUseCase(newsDao),
        selectArticlesUseCase = SelectArticlesUseCase(newsDao)
    )

}












