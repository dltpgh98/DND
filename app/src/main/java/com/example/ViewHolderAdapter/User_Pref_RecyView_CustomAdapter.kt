package com.example.ViewHolderAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ItemData.User_Pref_Item
import com.example.myapplication.databinding.UserPrefItemBinding

class User_Pref_RecyView_CustomAdapter(val Pref_ItemList : ArrayList<User_Pref_Item>) : RecyclerView.Adapter<User_Pref_RecyView_CustomAdapter.ViewHolder>(){

    interface ItemClick{  //클릭이벤트 추가부분
        fun onClick(view: View, position: Int)
    }
    var itemClick : ItemClick? = null // 클릭 이벤트 추가부분

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): User_Pref_RecyView_CustomAdapter.ViewHolder {
        val binding = UserPrefItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return Pref_ItemList.size
    }

    override fun onBindViewHolder(holder: User_Pref_RecyView_CustomAdapter.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }
        holder.prefItem.text = Pref_ItemList[position].prefItem;
    }


    /*
    값을 넣어줄 객체들을 입력
     */
    inner class ViewHolder(val binding: UserPrefItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val prefItem = binding.prefItemTv;
    }
}