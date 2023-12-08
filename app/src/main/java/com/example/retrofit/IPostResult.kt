package com.example.retrofit

import com.example.ItemData.BookMark_Item
import com.google.gson.JsonElement
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

interface RetrofitService2 {
    @GET("GetPreferencesNameList")
    fun getPreferencesNameList(
            @Query("preference_name") preference_name: String,
            @Query("like_option") like_option: Boolean

    ): Call<List<Preference>>

}

interface RetrofitService3{
    @GET("GetUserBookmarkList")
    fun getUserBookmarkList(
        @Query("user_id") user_id: String
    ): Call<List<BookMark_Item>>

}