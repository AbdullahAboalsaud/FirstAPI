package com.example.firstapi;

import com.example.firstapi.Models.ModelComments;
import com.example.firstapi.Models.ModelPosts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCalls {

    @GET("posts")
    Call<List<ModelPosts>> getPosts();

    @GET("comments")
    Call<List<ModelComments>> getComments(@Query("postId") int postId);


}
