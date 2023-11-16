package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofit.PostResult
import com.example.retrofit.RetrofitService
import db.main.MainDB
import kotlinx.android.synthetic.main.login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        // 기본 URL
        val BASE_URL = "http://172.16.104.69:8000/"

        // Retrofit 인터페이스를 구현한 서비스 인스턴스 생성 함수
        fun createRetrofitService(): RetrofitService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(RetrofitService::class.java)
        }

        login_bt.setOnClickListener{
            val id = login_id_et.text.toString()
            val pw = login_pw_et.text.toString()

            val retrofitService = createRetrofitService()
            val call: Call<PostResult> = retrofitService.getPosts(id, pw)
            // 호출을 비동기적으로 enqueue합니다.
            call.enqueue(object : retrofit2.Callback<PostResult> {
                override fun onResponse(call: Call<PostResult>, response: retrofit2.Response<PostResult>) {
                    if (response.isSuccessful) {
                        val postResult: PostResult? = response.body()
                        println(postResult)
                        val login_yn = postResult?.getLoginYn()
                        val message = postResult?.getMessage()
                        if(login_yn == "y"){
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                        } else{
                            // 로그인 실패 창
                        }
                    } else {
                        println("에러: ${response.code()}")
                    }
                }
                override fun onFailure(call: Call<PostResult>, t: Throwable) {
                    println("호출 실패: ${t.message}")
                }
            })



        }

        login_enter_tv.setOnClickListener{

        }
    }
}