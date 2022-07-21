package com.example.usecasejava.api;

import com.example.usecasejava.api.services.PostService;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiWorker {

    private static ApiWorker instance = null;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private final PostService postService;

    private ApiWorker()
    {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        clientBuilder.addInterceptor(loggingInterceptor);

        Retrofit retrofit= new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                //You need to tell Retrofit that you want to use RxJava 3
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(clientBuilder.build())
                .build();

        postService = retrofit.create(PostService.class);
    }
    public static synchronized ApiWorker getInstance() {

        if (instance == null) {

        instance = new ApiWorker();

        }

        return instance;
    }


    public PostService getPostService(){
        return postService;
    }
}
