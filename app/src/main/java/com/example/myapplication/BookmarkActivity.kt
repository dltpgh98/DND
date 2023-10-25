package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ItemData.BookMark_Item
import com.example.ViewHolderAdapter.Bookmark_RecyView_CustomAdapter
import kotlinx.android.synthetic.main.bookmark_frame.*
import kotlinx.android.synthetic.main.login.*

class BookmarkActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bookmark_frame)

        val book_ItemList = ArrayList<BookMark_Item>()
        book_ItemList.add(
            BookMark_Item("@drawable/book_img","도서명","저자","출판사",
                "tag1","tag2","tag3","tag4","tag5","tag6",
                "2023-10-25 11:05")
        )
        book_ItemList.add(
            BookMark_Item("@drawable/book_img","도서명","저자","출판사",
                "tag1","tag2","tag3","tag4","tag5","tag6",
                "2023-10-25 11:05")
        )
        book_ItemList.add(
            BookMark_Item("@drawable/book_img","도서명","저자","출판사",
                "tag1","tag2","tag3","tag4","tag5","tag6",
                "2023-10-25 11:05")
        )
        book_ItemList.add(
            BookMark_Item("@drawable/book_img","도서명","저자","출판사",
                "tag1","tag2","tag3","tag4","tag5","tag6",
                "2023-10-25 11:05")
        )
        book_ItemList.add(
            BookMark_Item("@drawable/book_img","도서명","저자","출판사",
                "tag1","tag2","tag3","tag4","tag5","tag6",
                "2023-10-25 11:05")
        )
        book_ItemList.add(
            BookMark_Item("@drawable/book_img","도서명","저자","출판사",
                "tag1","tag2","tag3","tag4","tag5","tag6",
                "2023-10-25 11:05")
        )
        val adapter = Bookmark_RecyView_CustomAdapter(book_ItemList)
        bookmark_list_rv.adapter = adapter
        bookmark_list_rv.adapter = Bookmark_RecyView_CustomAdapter(book_ItemList)
        bookmark_list_rv.layoutManager = LinearLayoutManager(this)
        adapter.itemClick = object : Bookmark_RecyView_CustomAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                Toast.makeText(this@BookmarkActivity,"선택", Toast.LENGTH_LONG).show()
            }
        }

    }
}