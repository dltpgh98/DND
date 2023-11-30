package com.example.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("GetUserLoginCheck")
    fun getPosts(
        @Query("id") id: String,
        @Query("pwd") pwd: String
    ): Call<PostResult>
}