package com.gigawattstechnology.e_lib;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBookInstance {
    private static Retrofit retrofit;
    private static String BASEURL="https://www.googleapis.com/books/v1/volumes?q=novels/";

    public static Retrofit getRetrofit() {
        if(retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(BASEURL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }
}
