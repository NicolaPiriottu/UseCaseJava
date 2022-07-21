package com.example.usecasejava.api.services;

import com.example.usecasejava.models.PostModel;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface PostService {

    @GET("posts")
    Single<List<PostModel>> listPosts();
}
