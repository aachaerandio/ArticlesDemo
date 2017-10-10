package com.aachaerandio.articlesdemo.domain;

import com.aachaerandio.articlesdemo.model.ArticleDetailsWrapper;
import com.aachaerandio.articlesdemo.service.ArticlesApiService;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetArticleDetailsUseCase {

    ArticlesApiService articlesApiService;

    @Inject
    public GetArticleDetailsUseCase(ArticlesApiService articlesApiService) {
        this.articlesApiService = articlesApiService;
    }

    public Observable<ArticleDetailsWrapper> execute(String articleId){
        return articlesApiService.getService().getArticleById(articleId);
    }
}
