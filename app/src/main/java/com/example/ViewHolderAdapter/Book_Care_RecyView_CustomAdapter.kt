package com.example.ViewHolderAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ItemData.Book_Care_Item
import com.example.myapplication.databinding.BookCareInfoItemBinding

//https://velog.io/@seokgyuhong/Android-Kotlin-Fundamentals8.2-Loading-and-displaying-images-from-the-Internet

class Book_Care_RecyView_CustomAdapter(val bookCareItemList: ArrayList<Book_Care_Item>) : RecyclerView.Adapter<Book_Care_RecyView_CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Book_Care_RecyView_CustomAdapter.ViewHolder {
        val binding = BookCareInfoItemBinding.inflate(LayoutInflater.from(parent.context),parent
        ,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: Book_Care_RecyView_CustomAdapter.ViewHolder, position: Int) {
        holder.title.text = bookCareItemList[position].title
        holder.info.text = bookCareItemList[position].info
    }

    override fun getItemCount(): Int {
        return bookCareItemList.size
    }

    inner class ViewHolder(val binding: BookCareInfoItemBinding) : RecyclerView.ViewHolder(binding.root){
        val title = binding.bookCareTitleTv
        val info = binding.bookCareInfoTv
    }

}