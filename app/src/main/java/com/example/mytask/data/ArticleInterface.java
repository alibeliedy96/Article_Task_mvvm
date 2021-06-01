package com.example.mytask.data;

import com.example.mytask.model.ArticleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ArticleInterface {
    @GET("mostpopular/v2/viewed/7.json")

  Call<ArticleResponse> getArticle(@Query("api-key") String api_key);
}
