package com.example.ViewHolderAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ItemData.BookMark_Item
import com.example.myapplication.databinding.BookmarkItemBinding

class Bookmark_RecyView_CustomAdapter(val book_ItemList : ArrayList<BookMark_Item>) : RecyclerView.Adapter<Bookmark_RecyView_CustomAdapter.ViewHolder>(){

    interface ItemClick{  //클릭이벤트 추가부분
        fun onClick(view: View, position: Int)
    }
    var itemClick : ItemClick? = null // 클릭 이벤트 추가부분

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Bookmark_RecyView_CustomAdapter.ViewHolder {
        val binding = BookmarkItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return book_ItemList.size
    }

    override fun onBindViewHolder(holder: Bookmark_RecyView_CustomAdapter.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }

//        holder.img.drawable = book_ItemList[position].Img
        holder.title.text = book_ItemList[position].title
        holder.writer.text = book_ItemList[position].writer
        holder.publisher.text = book_ItemList[position].publisher
        holder.tag1.text = book_ItemList[position].tag1
        holder.tag2.text = book_ItemList[position].tag2
        holder.tag3.text = book_ItemList[position].tag3
        holder.tag4.text = book_ItemList[position].tag4
        holder.tag5.text = book_ItemList[position].tag5
        holder.tag6.text = book_ItemList[position].tag6
        holder.pickDate.text = book_ItemList[position].pickDate
    }


    /*
    값을 넣어줄 객체들을 입력
     */
    inner class ViewHolder(val binding: BookmarkItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val img = binding.bookmarkTitleImgIv
        val title = binding.bookmarkTitleTv
        val writer = binding.bookmarkWriterTv
        val publisher = binding.bookmarkPublisherTv
        val tag1 = binding.bookmarkPreferTag1Tv
        val tag2 = binding.bookmarkPreferTag2Tv
        val tag3 = binding.bookmarkPreferTag3Tv
        val tag4 = binding.bookmarkPreferTag4Tv
        val tag5 = binding.bookmarkPreferTag5Tv
        val tag6 = binding.bookmarkPreferTag6Tv
        val pickDate = binding.bookmarkDateTv
    }
}