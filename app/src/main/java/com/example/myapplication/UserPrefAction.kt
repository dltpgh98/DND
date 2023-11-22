package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
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