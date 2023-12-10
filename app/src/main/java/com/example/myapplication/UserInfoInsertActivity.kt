package com.example.myapplication

import android.content.Intent
import android.graphics.Color.blue
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.retrofit.RetrofitService2
import com.example.retrofit.RetrofitService_UserInfoInsert
import com.example.retrofit.User_Info
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.user_info.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class UserInfoInsertActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_info)
        val userInfo: User_Info? = intent.getParcelableExtra("userInfo")
        val id: String? = intent.getStringExtra("id")
        var isClick = false
        var age = 0
        val today = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val currentDateTimeString = dateFormat.format(today.time) + ".000000" // 마이크로초 직접 추가
        user_select_age_dp.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH), null)



        val retrofit = Retrofit.Builder()
                .baseUrl("http://172.30.1.72:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val api = retrofit.create(RetrofitService_UserInfoInsert::class.java)


        user_gen_mr_bt.setOnClickListener {
            it.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_blue_light))
            user_gen_ms_bt.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray))
            isClick = true
        }
        user_gen_ms_bt.setOnClickListener {
            it.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_blue_light))
            user_gen_mr_bt.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray))
            isClick = false
        }
        user_select_age_dp.init(user_select_age_dp.year, user_select_age_dp.month, user_select_age_dp.dayOfMonth,
                DatePicker.OnDateChangedListener { _, year, monthOfYear, dayOfMonth ->
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(year, monthOfYear, dayOfMonth)

                    val currentDate = Calendar.getInstance()

                    age = currentDate.get(Calendar.YEAR) - selectedDate.get(Calendar.YEAR)

                    // 생일이 아직 지나지 않은 경우, 연령에서 -1을 해줍니다.
                    if (currentDate.get(Calendar.DAY_OF_YEAR) < selectedDate.get(Calendar.DAY_OF_YEAR)) {
                        age--
                    }

                }
        )


        user_next_bt.setOnClickListener{
            var gender : String
            if (isClick == true){
                gender = "m"
            }
            else{
                gender = "w"
            }
            val userNickname = user_name_et.text.toString() // 닉네임
            val userAge = age // 나이
            val userTime = currentDateTimeString // 현재시간
            val userId = id.toString() //아이디

            val call = api.PostUpdateUserInfo(userNickname, gender, userAge, userTime, userId)
            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        Log.d("RetrofitService", "Response: ${response.body()?.string()}")
                        val intent = Intent(this@UserInfoInsertActivity, UserPrefAction::class.java)
                        intent.putExtra("id", id)
                        intent.putExtra("userInfo", userInfo)
                        startActivity(intent)
                    } else {
                        Log.d("RetrofitService", "Post failed with status code: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.d("RetrofitService", "Error: ${t.message}")
                }
            })
        }

    }
}