package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.*
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

    private lateinit var adapter: MainRecyView_CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_frame)

        // Retrofit 설정
        val baseURL = "http://192.168.219.105:8000/"
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // 리사이클러뷰 설정
        adapter = MainRecyView_CustomAdapter(emptyList())
        main_book_list_rv.layoutManager = GridLayoutManager(this, 2)
        main_book_list_rv.adapter = adapter

        main_floating_fbtn.setOnClickListener{
            val intent = Intent(this, BookmarkActivity::class.java)
            startActivity(intent)
        }
        // 서버 통신
        val api = retrofit.create(RetrofitService_GetBookSearch::class.java)

        var searchBook = ""
        getItemsFromServer(searchBook,api)
        // 버튼 클릭 이벤트 리스너
        findViewById<ImageButton>(R.id.main_search_ibtn).setOnClickListener {
            // 검색어를 입력받습니다.
            searchBook = findViewById<EditText>(R.id.main_search_bar_et).text.toString()
            getItemsFromServer(searchBook,api)

        }

        adapter.itemClick = object : MainRecyView_CustomAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                // 선택된 아이템의 데이터 가져오기
                Toast.makeText(this@MainActivity,"선택: $view",Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun getItemsFromServer(searchBook: String = "",api: RetrofitService_GetBookSearch) {
        // 서버에 검색 요청을 보냅니다.
        api.getBookSearch(searchBook).enqueue(object : Callback<List<MainBook_Item>> {
            override fun onResponse(call: Call<List<MainBook_Item>>, response: Response<List<MainBook_Item>>) {
                // 통신 성공 시
                Log.d("통신 성공", "북마크 목록 가져옴")
                val bookItemList = response.body()
                if (bookItemList != null) {
                    // 어댑터에 데이터 설정 및 리사이클러뷰에 연결
                    adapter.updateItems(bookItemList)
                }
            }

            override fun onFailure(call: Call<List<MainBook_Item>>, t: Throwable) {
                // 통신 실패 시
                Log.e("통신 실패", t.message.toString())
            }
        })
    }
}