package com.example.retrofit

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class PostResult_Preference {

    @SerializedName("preferences")
    val preferences: List<Preference>? = null

     //toString()을 Override 해주지 않으면 객체 주소값을 출력함
    override fun toString(): String {
        return "PostResult_Preference{" +
               preferences +"}"
    }
}

data class Preference(
        @SerializedName("p_id") val id: Int,
        @SerializedName("p_name") val name: String
)

