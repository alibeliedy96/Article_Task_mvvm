package com.example.mytask.data;

import android.util.Log;

import com.example.mytask.constant.AppConstant;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticleClient {


    private static Retrofit retrofitInstance;
    private static Retrofit getInstance()
    {

        HttpLoggingInterceptor loggingInterceptor =new HttpLoggingInterceptor(
                new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.e("api",message);
                    }
                });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        if(retrofitInstance==null)
        {

            retrofitInstance = new Retrofit.Builder()
                    .baseUrl(AppConstant.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitInstance;
    }
    public static ArticleInterface getApi()
    {
        return getInstance().create(ArticleInterface.class);
    }

}
