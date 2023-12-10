package com.example.ViewHolderAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ItemData.BookMark_Item
import com.example.myapplication.databinding.BookmarkItemBinding

class Bookmark_RecyView_CustomAdapter(var book_ItemList : List<BookMark_Item>) : RecyclerView.Adapter<Bookmark_RecyView_CustomAdapter.ViewHolder>(){

    private var context: Context? = null
    constructor(book_ItemList: List<BookMark_Item>, context: Context) : this(book_ItemList) {
        this.context = context
    }


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
        // holder.img.drawable = Glide(book_ItemList[position].b_img)
        //Glide.with(context!!).load(book_ItemList[position].b_img);
//        holder.img.setImageDrawable(Glide.with(context).load(book_ItemList[position].b_img))
        holder.title.text = book_ItemList[position].b_name
        holder.writer.text = book_ItemList[position].b_aut
        holder.publisher.text = book_ItemList[position].b_ps
        holder.shortInfo.text = book_ItemList[position].b_short
        holder.pickDate.text = book_ItemList[position].b_regist.toString()
    }

//    override fun onBindViewHolder(holder: Bookmark_RecyView_CustomAdapter.ViewHolder, position: Int) {
//        holder.itemView.setOnClickListener {
//            itemClick?.onClick(it, position)
//        }
//       // holder.img.drawable = Glide(book_ItemList[position].b_img)
//        Glide.with(context).load(book_ItemList[position].b_img);
//        holder.img.drawable
//        holder.title.text = book_ItemList[position].b_name
//        holder.writer.text = book_ItemList[position].b_aut
//        holder.publisher.text = book_ItemList[position].b_ps
//        holder.shortInfo.text = book_ItemList[position].b_short
//        holder.pickDate.text = book_ItemList[position].b_regist.toString()
//    }


    /*
    값을 넣어줄 객체들을 입력
     */
    inner class ViewHolder(val binding: BookmarkItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val img = binding.bookmarkTitleImgIv
        val title = binding.bookmarkTitleTv
        val writer = binding.bookmarkWriterTv
        val publisher = binding.bookmarkPublisherTv
        val shortInfo = binding.bookmarkShortTv
        val pickDate = binding.bookmarkDateTv
    }
    fun updateItems(newBookMarkItemList: List<BookMark_Item>) {
        book_ItemList = newBookMarkItemList
        notifyDataSetChanged()
    }
}