package com.aachaerandio.articlesdemo.domain.di;

import com.aachaerandio.articlesdemo.domain.GetArticleDetailsUseCase;
import com.aachaerandio.articlesdemo.domain.GetArticlesUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

@Module
public class TestArticleModule {

    @Provides
    @Singleton
    GetArticlesUseCase provideGetArticles() {
        return mock(GetArticlesUseCase.class);
    }

    @Provides
    @Singleton
    GetArticleDetailsUseCase provideGetArticleDetails() {
        return mock(GetArticleDetailsUseCase.class);
    }
}
