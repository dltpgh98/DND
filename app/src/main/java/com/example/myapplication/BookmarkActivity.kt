package com.example.myapplication

import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ItemData.BookMark_Item
import com.example.ViewHolderAdapter.Bookmark_RecyView_CustomAdapter
import com.example.retrofit.RetrofitService_GetUserBookmarkList
import com.example.retrofit.User_Info
import kotlinx.android.synthetic.main.bookmark_frame.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BookmarkActivity: AppCompatActivity() {

    private lateinit var adapter: Bookmark_RecyView_CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bookmark_frame)
        val baseURL = "http://192.168.219.105:8000/"

        // 빈 어댑터 선언
        adapter = Bookmark_RecyView_CustomAdapter(emptyList())

        val userInfo: User_Info? = intent.getParcelableExtra("userInfo")
        val id: String? = intent.getStringExtra("id")
        val pw: String? = intent.getStringExtra("pw")

        Log.d("id", id+"")

        // 리사이클러뷰 레이아웃 매니저 설정
        bookmark_list_rv.layoutManager = LinearLayoutManager(this)
        bookmark_list_rv.adapter = adapter
        // Retrofit 객체 생성 및 API 인터페이스 가져오기
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val api = retrofit.create(RetrofitService_GetUserBookmarkList::class.java)

        // 스레드 확인 로그
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Log.d("ThreadCheck", "메인 쓰레드에서 실행 중")
        } else {
            Log.d("ThreadCheck", "메인 쓰레드가 아닌 곳에서 실행 중")
        }

        // 서버 통신 - 북마크 목록 가져오기
        api.getUserBookmarkList("admin").enqueue(object : Callback<List<BookMark_Item>> {
            override fun onResponse(call: Call<List<BookMark_Item>>, response: Response<List<BookMark_Item>>) {
                // 통신 성공 시
                Log.d("통신 성공", "북마크 목록 가져옴")
                val bookMarkItemList = response.body()
                if (bookMarkItemList != null) {
                    Log.d("실행안함?4", "뭐함?")
                    // 어댑터에 데이터 설정 및 리사이클러뷰에 연결
                    adapter.updateItems(bookMarkItemList)
                    print("실행함?")
                } else {
                    Log.d("실행안함?5", "뭐함?")
                }
            }
            override fun onFailure(call: Call<List<BookMark_Item>>, t: Throwable) {
                // 통신 실패 시
                Log.e("통신 실패", t.message.toString())
            }
        })
        // 아이템 클릭 리스너 설정
        adapter.itemClick = object : Bookmark_RecyView_CustomAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                Toast.makeText(this@BookmarkActivity, "선택: $position", Toast.LENGTH_SHORT).show()
            }
        }
    }
//        api.getUserBookmarkList("admin").enqueue(object : Callback<List<BookMark_Item>> {
//            override fun onResponse(call: Call<List<BookMark_Item>>, response: Response<List<BookMark_Item>>) {
//                Log.d("실행안함?3", "뭐함?")
//                val bookMarkItemList = response.body()!!
//                bookmark_list_rv.adapter = Bookmark_RecyView_CustomAdapter(bookMarkItemList)
//                bookmark_list_rv.adapter = adapter
//
////                    adapter.updateData(preferenceList)
//            }
//
//            override fun onFailure(call: Call<List<BookMark_Item>>, t: Throwable) {
//                // 통신 실패 처리
//                Log.d("통신 : ", "실패 처리")
//            }
//        })

//
//        val book_ItemList = ArrayList<BookMark_Item>()
//        book_ItemList.add(
//            BookMark_Item("@drawable/book_img","도서명","저자","출판사",
//                "tag1","tag2","tag3","tag4","tag5","tag6",
//                "2023-10-25 11:05")
//        )

//        val adapter = Bookmark_RecyView_CustomAdapter(book_ItemList)
//        bookmark_list_rv.adapter = adapter
//        bookmark_list_rv.adapter = Bookmark_RecyView_CustomAdapter(book_ItemList)
//        bookmark_list_rv.layoutManager = LinearLayoutManager(this)
//        adapter.itemClick = object : Bookmark_RecyView_CustomAdapter.ItemClick {
//            override fun onClick(view: View, position: Int) {
//                Toast.makeText(this@BookmarkActivity,"선택", Toast.LENGTH_LONG).show()
//            }
//        }

//    }
}