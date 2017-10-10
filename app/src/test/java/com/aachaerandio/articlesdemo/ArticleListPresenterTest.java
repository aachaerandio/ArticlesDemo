package com.aachaerandio.articlesdemo;

import com.aachaerandio.articlesdemo.domain.GetArticlesUseCase;
import com.aachaerandio.articlesdemo.domain.di.DaggerTestArticleComponent;
import com.aachaerandio.articlesdemo.model.Article;
import com.aachaerandio.articlesdemo.model.ArticlesWrapper;
import com.aachaerandio.articlesdemo.presenter.ArticleListPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ArticleListPresenterTest extends RxJavaSchedulerTest {

    @Inject
    GetArticlesUseCase getArticlesUseCase;
    @Inject
    ArticleListPresenter articleListPresenter;
    @Mock
    private ArticleListPresenter.UserInterface mockView;

    @Before
    public void setup(){
        DaggerTestArticleComponent.builder().build().inject(this);
    }

    @Test
    public void givenArticlesLoaded_ThenShowArticles() throws Exception {
        articleListPresenter.setView(mockView);

        when(getArticlesUseCase.execute()).thenReturn(Observable.just(createArticleList()));

        articleListPresenter.loadArticles();

        ArgumentCaptor<List<Article>> argumentCaptor = ArgumentCaptor.forClass(ArrayList.class);

        verify(mockView, times(1)).showLoading(eq(true));
        verify(mockView, times(1)).showLoading(eq(false));
        verify(mockView, times(1)).showArticles(argumentCaptor.capture());

        assertEquals(1, argumentCaptor.getValue().size());
        assertEquals(Integer.valueOf(1), argumentCaptor.getValue().get(0).getId());
    }

    @Test
    public void givenLoadArticlesFailed_ThenShowError() throws Exception {
        articleListPresenter.setView(mockView);

        Observable<ArticlesWrapper> testError = Observable.error(new RuntimeException("TestError"));

        when(getArticlesUseCase.execute()).thenReturn(testError);

        articleListPresenter.loadArticles();

        verify(mockView, times(1)).showLoading(eq(true));
        verify(mockView, times(1)).showErrorMessage("TestError");
    }

    private ArticlesWrapper createArticleList() {
        ArticlesWrapper wrapper = new ArticlesWrapper();
        Article article = new Article();
        article.setId(1);
        article.setTitle("Title 1");
        wrapper.setArticles(Arrays.asList(article));

        return wrapper;
    }
}
