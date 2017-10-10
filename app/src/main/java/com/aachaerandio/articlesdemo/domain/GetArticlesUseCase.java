package com.aachaerandio.articlesdemo.domain;

import com.aachaerandio.articlesdemo.ArticlesApiService;
import com.aachaerandio.articlesdemo.BuildConfig;
import com.aachaerandio.articlesdemo.model.ArticlesWrapper;

import io.reactivex.Observable;

public class GetArticlesUseCase {

    ArticlesApiService articlesApiService = new ArticlesApiService(BuildConfig.CONTENTLIST_BASE_URL);

    public Observable<ArticlesWrapper> execute(){
        return articlesApiService.getService().getArticles();
    }
}
