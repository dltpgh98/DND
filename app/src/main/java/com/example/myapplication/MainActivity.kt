package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ItemData.BookMark_Item
import com.example.ItemData.MainBook_Item
import com.example.ViewHolderAdapter.MainRecyView_CustomAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.retrofit.RetrofitService_GetBookSearch
import com.example.retrofit.RetrofitService_GetUserBookmarkList
//import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_frame.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
//    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var adapter: MainRecyView_CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
        setContentView(R.layout.main_frame)
        val baseURL = "http://192.168.219.105:8000/"

        main_floating_fbtn.setOnClickListener{
            val intent = Intent(this, BookmarkActivity::class.java)
            startActivity(intent)
        }
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        adapter = MainRecyView_CustomAdapter(emptyList())
        main_book_list_rv.layoutManager = GridLayoutManager(this,2)
        main_book_list_rv.adapter = adapter
        val api = retrofit.create(RetrofitService_GetBookSearch::class.java)

        var searchBook = "";
        // 스레드 확인 로그
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Log.d("ThreadCheck", "메인 쓰레드에서 실행 중")
        } else {
            Log.d("ThreadCheck", "메인 쓰레드가 아닌 곳에서 실행 중")
        }
//        val book_ItemList = ArrayList<MainBook_Item>()
        // 서버 통신 - 북마크 목록 가져오기
        api.getBookSearch(searchBook).enqueue(object : Callback<List<MainBook_Item>> {
            override fun onResponse(call: Call<List<MainBook_Item>>, response: Response<List<MainBook_Item>>) {
                // 통신 성공 시
                Log.d("통신 성공", "북마크 목록 가져옴")
                val bookItemList = response.body()
                if (bookItemList != null) {
                    Log.d("실행안함?4", "뭐함?")
                    // 어댑터에 데이터 설정 및 리사이클러뷰에 연결
                    adapter.updateItems(bookItemList)
                    print("실행함?")
                } else {
                    Log.d("실행안함?5", "뭐함?")
                }
            }
            override fun onFailure(call: Call<List<MainBook_Item>>, t: Throwable) {
                // 통신 실패 시
                Log.e("통신 실패", t.message.toString())
            }
        })
//        val adapter = MainRecyView_CustomAdapter(bookItemList)

//        main_book_list_rv.adapter = MainRecyView_CustomAdapter(book_ItemList)
//        main_book_list_rv.layoutManager = LinearLayoutManager(this)

        adapter.itemClick = object : MainRecyView_CustomAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                Toast.makeText(this@MainActivity,"선택",Toast.LENGTH_LONG).show()
            }
        }


//        setContentView(R.layout.db_main)

//        setContentView(R.layout.login)

/*
        setContentView(R.layout.activity_main)
        txt_id.setOnClickListener{
            val text1 = txt_id.text.toString()
        }
*/

    }
}