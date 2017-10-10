package com.aachaerandio.articlesdemo.domain.di;

import com.aachaerandio.articlesdemo.domain.GetArticleDetailsUseCase;
import com.aachaerandio.articlesdemo.service.ArticlesApiService;
import com.aachaerandio.articlesdemo.domain.GetArticlesUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ArticlesModule {

    @Provides
    @Singleton
    GetArticlesUseCase provideGetArticles(ArticlesApiService apiService) {
        return new GetArticlesUseCase(apiService);
    }

    @Provides
    @Singleton
    GetArticleDetailsUseCase provideGetArticleDetails(ArticlesApiService apiService) {
        return new GetArticleDetailsUseCase(apiService);
    }

}
