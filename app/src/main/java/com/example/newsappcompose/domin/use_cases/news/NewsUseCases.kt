package com.example.newsappcompose.domin.use_cases.news

data class NewsUseCases(
    val getNewsUseCase: GetNewsUseCase ,
    val searchNewsUseCase: SearchNewsUseCase  ,
    val upsertArticleUseCase: UpsertArticleUseCase ,
    val selectArticleUseCase: SelectArticleUseCase,
    val deleteArticleUseCase: DeleteArticleUseCase,
    val selectArticlesUseCase: SelectArticlesUseCase

)
