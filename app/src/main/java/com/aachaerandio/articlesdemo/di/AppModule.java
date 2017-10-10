package com.aachaerandio.articlesdemo.di;

import com.aachaerandio.articlesdemo.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AppModule {
    @ContributesAndroidInjector
    abstract MainActivity provideMainActivity();
}
