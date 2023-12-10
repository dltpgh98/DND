package com.example.myapplication

import PreferenceAdapter
import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ItemData.User_Pref_Item
import com.example.retrofit.*
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.user_pref.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserPrefAction: AppCompatActivity() {

    private lateinit var adapter: PreferenceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_pref)

        adapter = PreferenceAdapter(emptyList())

        val userInfo: User_Info? = intent.getParcelableExtra("userInfo")
        val id: String? = intent.getStringExtra("id")
        //val pw: String? = intent.getStringExtra("pw")

        user_pref_list_rv.layoutManager = GridLayoutManager(this, 2)
        user_pref_list_rv.adapter = adapter
        val BASE_URL = "http://172.30.1.72:8000/" //이세호 집


        val retrofit = Retrofit.Builder()
                .baseUrl("http://172.30.1.100:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        Log.d("실행안함1?", "뭐함?")

        val api = retrofit.create(RetrofitService2::class.java)
        val api2 = retrofit.create(RetrofitService_UserPreferenceInsert::class.java)
        Log.d("실행안함?2", "뭐함?")
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Log.d("ThreadCheck", "메인 쓰레드에서 실행 중")
        } else {
            Log.d("ThreadCheck", "메인 쓰레드가 아닌 곳에서 실행 중")
        }
        api.getPreferencesNameList("", true).enqueue(object : Callback<List<Preference>> {
            override fun onResponse(call: Call<List<Preference>>, response: Response<List<Preference>>) {
                Log.d("실행안함?3", "뭐함?")
                val preferenceList = response.body()
                if (preferenceList != null) {
                    Log.d("실행안함?4", "뭐함?")
                    adapter.updateData(preferenceList)
                    print("실행함?")
                } else {
                    Log.d("실행안함?5", "뭐함?")
                }
                Log.d("실행안함?6", "뭐함?")
            }

            override fun onFailure(call: Call<List<Preference>>, t: Throwable) {
                // API 호출에 실패했을 때의 처리를 여기에 작성하세요.
                Log.e("NetworkError", "네트워크 요청 실패", t)
            }
        })
        //유처취향정보입력
        fun savePreferencesToDB(selectedItems: Set<Int>) {
            val userId = id.toString() // 사용자 ID
            val preferenceIds = selectedItems.joinToString(",")
            print("확인" + selectedItems)
            val call = api2.postUserPreferences(userId, preferenceIds)


            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        // 요청 성공
                        Log.d("NetworkSuccess", "요청 성공") // 성공 로그 추가
                        val intent = Intent(this@UserPrefAction, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        // 서버에서 오류 메시지 반환
                        val errorMsg = response.errorBody()?.string() ?: "Unknown error"
                        Log.e("ServerError", "서버에서 오류 메시지 반환: $errorMsg") // 서버 오류 로그 추가
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    // 네트워크 오류 등
                    Log.e("NetworkError2", "네트워크 요청 실패", t)
                }
            })
        }

        user_pref_ok_bt.setOnClickListener{
            val selectedItems = adapter.getSelectedItems()

            print("선택한 아이탬" + selectedItems)
            savePreferencesToDB(selectedItems)
            Log.d("확인버튼", "클릭")

        }



    }

}

/*
import com.example.ItemData.User_Pref_Item
import com.example.ViewHolderAdapter.User_Pref_RecyView_CustomAdapter
import kotlinx.android.synthetic.main.user_pref.*

class UserPrefAction: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_pref)
        val pref_ItemList = ArrayList<User_Pref_Item>()
        pref_ItemList.add(User_Pref_Item("판타지"))
        pref_ItemList.add(User_Pref_Item("로맨스"))
        pref_ItemList.add(User_Pref_Item("취향"))
        pref_ItemList.add(User_Pref_Item("교양"))
        pref_ItemList.add(User_Pref_Item("일상"))
        pref_ItemList.add(User_Pref_Item("취향"))
        pref_ItemList.add(User_Pref_Item("액션"))
        pref_ItemList.add(User_Pref_Item("취향"))
        pref_ItemList.add(User_Pref_Item("취향"))

        val adapter = User_Pref_RecyView_CustomAdapter(pref_ItemList)
        user_pref_list_rv.adapter = adapter
        //user_pref_list_rv.adapter = User_Pref_RecyView_CustomAdapter(pref_ItemList)
        user_pref_list_rv.layoutManager = GridLayoutManager(this,2)
        adapter.itemClick = object : User_Pref_RecyView_CustomAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                Toast.makeText(this@UserPrefAction, "선택",Toast.LENGTH_LONG).show()
            }

        }
    }
}
*/
