package com.example.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("UserLoginCheck")
    fun getPosts(
        @Query("id") id: String,
        @Query("pwd") pwd: String
    ): Call<PostResult>
}