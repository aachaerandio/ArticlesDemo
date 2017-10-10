package com.aachaerandio.articlesdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.aachaerandio.articlesdemo.Constants;
import com.aachaerandio.articlesdemo.R;

public class MainActivity extends AppCompatActivity implements ArticleListFragment.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolBar();

        ArticleListFragment articlesListFragment = new ArticleListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, articlesListFragment).commit();
    }

    private void setToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle(R.string.app_name);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
    }

    @Override
    public void onArticleClicked(String articleId) {
        startArticleDetailsActivity(articleId);
    }

    private void startArticleDetailsActivity(String articleId) {
        Intent intent = new Intent(this, ArticleDetailsActivity.class);
        Bundle extras = new Bundle();
        extras.putString(Constants.ARTICLE_ID, articleId);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
