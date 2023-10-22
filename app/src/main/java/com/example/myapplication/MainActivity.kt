package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ItemData.MainBook_Item
import com.example.ViewHolderAdapter.MainRecyView_CustomAdapter
import com.example.myapplication.databinding.ActivityMainBinding
//import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_frame.*

class MainActivity : AppCompatActivity() {
//    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
        setContentView(R.layout.main_frame)

        val book_ItemList = ArrayList<MainBook_Item>()
        book_ItemList.add(MainBook_Item("test1","test1","test1","Img1","Title1","Writer1"))
        book_ItemList.add(MainBook_Item("test2","test2","test2","Img2","Title2","Writer2"))


        val adapter = MainRecyView_CustomAdapter(book_ItemList)
        main_book_list_rv.adapter = adapter
        main_book_list_rv.adapter = MainRecyView_CustomAdapter(book_ItemList)
        main_book_list_rv.layoutManager = LinearLayoutManager(this)

        adapter.itemClick = object : MainRecyView_CustomAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                Toast.makeText(this@MainActivity,"test",Toast.LENGTH_LONG).show()
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