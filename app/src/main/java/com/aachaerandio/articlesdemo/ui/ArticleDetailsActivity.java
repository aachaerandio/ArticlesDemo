package com.aachaerandio.articlesdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.aachaerandio.articlesdemo.Constants;
import com.aachaerandio.articlesdemo.R;

public class ArticleDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);

        setToolBar();

        if (savedInstanceState == null)
        {
            Bundle extras = getIntent().getExtras();
            if (extras != null && extras.containsKey(Constants.ARTICLE_ID))
            {
                String articleId = extras.getString(Constants.ARTICLE_ID);
                ArticleDetailsFragment articleDetailsFragment = ArticleDetailsFragment.getInstance(articleId);
                getSupportFragmentManager().beginTransaction().replace(R.id.article_details_container, articleDetailsFragment).commit();
            }
        }
    }

    private void setToolBar() {
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle(R.string.article_details);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
    }
}
