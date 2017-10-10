package com.aachaerandio.articlesdemo.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArticleDetailsWrapper {

    @SerializedName("item")
    @Expose
    public Article article;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
