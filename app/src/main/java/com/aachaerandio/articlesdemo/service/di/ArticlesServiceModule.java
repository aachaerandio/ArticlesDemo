package com.aachaerandio.articlesdemo.service.di;

import com.aachaerandio.articlesdemo.BuildConfig;
import com.aachaerandio.articlesdemo.service.ArticlesApiService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ArticlesServiceModule {

    @Provides
    @Singleton
    ArticlesApiService provideArticlesApiService(@Named("url") String serviceUrl){
        return new ArticlesApiService(serviceUrl);
    }

    @Provides
    @Named("url")
    String provideServiceUrl(){
        return BuildConfig.CONTENTLIST_BASE_URL;
    }
}
