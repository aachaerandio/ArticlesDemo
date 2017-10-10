package com.aachaerandio.articlesdemo;

import com.aachaerandio.articlesdemo.domain.GetArticleDetailsUseCase;
import com.aachaerandio.articlesdemo.domain.di.DaggerTestArticleComponent;
import com.aachaerandio.articlesdemo.model.Article;
import com.aachaerandio.articlesdemo.model.ArticleDetailsWrapper;
import com.aachaerandio.articlesdemo.presenter.ArticleDetailsPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.inject.Inject;

import io.reactivex.Observable;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ArticleDetailsPresenterTest extends RxJavaSchedulerTest {

    @Inject
    GetArticleDetailsUseCase getArticleDetails;
    @Inject
    ArticleDetailsPresenter articleDetailsPresenter;
    @Mock
    private ArticleDetailsPresenter.UserInterface mockView;

    @Before
    public void setup(){
        DaggerTestArticleComponent.builder().build().inject(this);
    }

    @Test
    public void givenArticleDetailsLoaded_ThenShowDetails() throws Exception {
        articleDetailsPresenter.setView(mockView);

        when(getArticleDetails.execute("1")).thenReturn(Observable.just(createArticle()));

        articleDetailsPresenter.loadDetails("1");

        ArgumentCaptor<Article> argumentCaptor = ArgumentCaptor.forClass(Article.class);

        verify(mockView, times(1)).showDetails(argumentCaptor.capture());

        assertEquals(Integer.valueOf(1), argumentCaptor.getValue().getId());
    }

    @Test
    public void givenLoadDetailsFailed_ThenShowError() throws Exception {
        articleDetailsPresenter.setView(mockView);

        Observable<ArticleDetailsWrapper> testError = Observable.error(new RuntimeException("TestError"));

        when(getArticleDetails.execute("1")).thenReturn(testError);

        articleDetailsPresenter.loadDetails("1");

        verify(mockView, times(1)).showError("TestError");
    }

    private ArticleDetailsWrapper createArticle() {
        ArticleDetailsWrapper wrapper = new ArticleDetailsWrapper();
        Article article = new Article();
        article.setId(1);
        article.setTitle("Article 1");
        wrapper.setArticle(article);
        return wrapper;
    }
}
