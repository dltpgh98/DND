package com.example.ViewHolderAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ItemData.MainBook_Item
import com.example.myapplication.databinding.MainBookListItemBinding

import kotlinx.android.synthetic.main.main_book_list_item.view.*

/*
https://jutole.tistory.com/9
참조
 */

class MainRecyView_CustomAdapter(val book_ItemList : ArrayList<MainBook_Item>) : RecyclerView.Adapter<MainRecyView_CustomAdapter.ViewHolder>() {

    interface ItemClick{  //클릭이벤트 추가부분
        fun onClick(view: View, position: Int)
    }
    var itemClick : ItemClick? = null // 클릭 이벤트 추가부분

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyView_CustomAdapter.ViewHolder {
        val binding = MainBookListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return book_ItemList.size
    }


    override fun onBindViewHolder(holder: MainRecyView_CustomAdapter.ViewHolder, position: Int) {
        //클릭이벤트 추가
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }
        holder.tag1.text = book_ItemList[position].tag1
        holder.tag2.text = book_ItemList[position].tag2
        holder.tag3.text = book_ItemList[position].tag3
//        holder.img.drawable = book_ItemList[position].Img
        holder.title.text = book_ItemList[position].Title
        holder.writer.text = book_ItemList[position].Writer
    }
    /*
    값을 넣어줄 객체들을 입력
     */
    inner class ViewHolder(val binding: MainBookListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val tag1 = binding.mainPreTag1Bt
        val tag2 = binding.mainPreTag2Bt
        val tag3 = binding.mainPreTag3Bt
        val img = binding.mainBookImageIv
        val title = binding.mainBookTitleTv
        val writer = binding.mainWriterTv
    }

}