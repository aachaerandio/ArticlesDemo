package com.aachaerandio.articlesdemo.domain;

import com.aachaerandio.articlesdemo.model.Article;
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

    public Observable<ArticleDetailsWrapper> execute(String countryId){
        return articlesApiService.getService().getArticleById(countryId);
    }
}
