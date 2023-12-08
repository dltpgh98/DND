package com.example.ItemData

import android.graphics.drawable.Drawable
import com.google.gson.annotations.SerializedName
import java.util.*

data class MainBook_Item (val tag1: String, val tag2: String,val tag3: String , val Img: String ,val Title: String, val Writer: String)


data class BookMark_Item (
    @SerializedName("b_id") val b_id: Int,
    @SerializedName("b_regist") val b_regist : Date,
    @SerializedName("u_id") val u_id : Int,
    @SerializedName("b_name") val b_name : String,
    @SerializedName("b_aut") val b_aut : String,
    @SerializedName("b_ps") val b_ps : String,
    @SerializedName("b_date") val b_date : String,
    @SerializedName("b_short") val b_short : String,
    @SerializedName("b_detail") val b_detail : String,
    @SerializedName("b_img") val b_img : String,
    @SerializedName("p_names") val p_names : String
)

data class Book_Care_Item(val title: String, val info: String)
data class User_Pref_Item(val prefItem : String)