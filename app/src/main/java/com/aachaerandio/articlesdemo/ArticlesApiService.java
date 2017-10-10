package com.aachaerandio.articlesdemo;

import com.aachaerandio.articlesdemo.model.Article;
import com.aachaerandio.articlesdemo.model.ArticlesWrapper;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;


public class ArticlesApiService {

    private ArticlesService articlesService;

    public ArticlesApiService(String serviceUrl) {
        articlesService = new Retrofit.Builder()
                .baseUrl(serviceUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ArticlesService.class);
    }

    public ArticlesService getService() {
        return this.articlesService;
    }

    public interface ArticlesService {

        @GET("contentList.json")
        Observable<ArticlesWrapper> getArticles();

        @GET("content/{id}.json")
        Observable<Article> getArticleById(@Path("id") String id);
    }
}
