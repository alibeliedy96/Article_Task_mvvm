package com.example.mytask.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mytask.model.ArticleResponse;
import com.example.mytask.repository.ArticleRepository;

public class ArticleViewModel extends AndroidViewModel {

    private ArticleRepository articleRepository;
    private LiveData<ArticleResponse> articleResponseLiveData;

    public ArticleViewModel(@NonNull Application application)
    {
        super(application);
        articleRepository=new ArticleRepository();
        articleResponseLiveData=articleRepository.getArticle();
    }

    public LiveData<ArticleResponse> getArticlesData()
    {
        return articleResponseLiveData;
    }

}