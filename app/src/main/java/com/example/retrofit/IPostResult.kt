package com.example.retrofit

import com.google.gson.JsonElement
import okhttp3.ResponseBody
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

interface RetrofitService_UserPreferenceInsert {
    @GET("PostUserPreferences")
    fun postUserPreferences(
            @Query("user_id") user_id: String,
            @Query("preference_ids") preference_id: String

    ): Call<ResponseBody>
}

interface RetrofitService_UserInfoInsert {
    @GET("PostUpdateUserInfo")
    fun PostUpdateUserInfo(
            @Query("user_nicname") user_nicname: String,
            @Query("user_gender") user_gender: String,
            @Query("user_age") user_age: Int,
        @Query("user_time") user_time: String,
    @Query("user_id") user_id: String

    ): Call<ResponseBody>
}

