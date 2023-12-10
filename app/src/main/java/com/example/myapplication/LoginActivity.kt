package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofit.PostResult
import com.example.retrofit.RetrofitService
import com.example.retrofit.User_Info
import kotlinx.android.synthetic.main.login.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        //val BASE_URL = "http://172.16.114.90:8000/" //이세호 학교
        val BASE_URL = "http://172.16.104.69:8000/"
        val BASE_URL = "http://172.30.1.100:8000/" //이세호 집
      
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
                            val userInfo: User_Info? = postResult?.user_info
                            if (userInfo != null) {

                                val userId: String = userInfo.userId
                                val userPwd: String = userInfo.userPwd
                                val userName: String = userInfo.userName
                                val userNickname: String = userInfo.userNickname
                                val userDivision: String = userInfo.userDivision
                                val userGender: String = userInfo.userGender
                                val userAge: Int = userInfo.userAge
                                val userTime: String = userInfo.userTime

                                val intent = Intent(this@LoginActivity, UserInfoInsertActivity::class.java)
                                intent.putExtra("userInfo", userInfo)
                                intent.putExtra("BASE_URL", BASE_URL)
                                intent.putExtra("id", id)
                                intent.putExtra("pw", pw)
                                startActivity(intent)
                            }
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
    }
}