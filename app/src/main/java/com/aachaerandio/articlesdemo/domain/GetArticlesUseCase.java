package com.aachaerandio.articlesdemo.domain;

import com.aachaerandio.articlesdemo.model.ArticlesWrapper;
import com.aachaerandio.articlesdemo.service.ArticlesApiService;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetArticlesUseCase {

    ArticlesApiService articlesApiService;

    @Inject
    public GetArticlesUseCase(ArticlesApiService articlesApiService) {
        this.articlesApiService = articlesApiService;
    }

    public Observable<ArticlesWrapper> execute(){
        return articlesApiService.getService().getArticles();
    }
}
