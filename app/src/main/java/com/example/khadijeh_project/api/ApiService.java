package com.example.khadijeh_project.api;

import com.example.khadijeh_project.model.Data;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("posts")
    Call<Data> getPost();
}
