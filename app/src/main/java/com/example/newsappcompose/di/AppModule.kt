package com.example.newsappcompose.di

import android.app.Application
import com.example.newsappcompose.data.manager.LocalUserManagerImpl
import com.example.newsappcompose.data.remote.NewsApi
import com.example.newsappcompose.data.repository.NewsRepositoryImpl
import com.example.newsappcompose.domin.manager.LocalUserManager
import com.example.newsappcompose.domin.repository.NewsRepository
import com.example.newsappcompose.domin.use_cases.appentry.AppEntryUseCases
import com.example.newsappcompose.domin.use_cases.appentry.ReadAppEntry
import com.example.newsappcompose.domin.use_cases.appentry.SaveAppEntry
import com.example.newsappcompose.domin.use_cases.news.GetNewsUseCase
import com.example.newsappcompose.domin.use_cases.news.NewsUseCases
import com.example.newsappcompose.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
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
    fun provideNewsUseCases(newsRepository: NewsRepository) = NewsUseCases(
        getNewsUseCase = GetNewsUseCase(newsRepository)
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

}












