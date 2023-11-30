package com.example.retrofit

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class PostResult {
    @SerializedName("login_yn")
    private val login_yn = ""

    @SerializedName("message")
    private val message = ""

    @SerializedName("user_info")
    val user_info: User_Info?= null

    // login_yn에 대한 getter 메서드
    fun getLoginYn(): String {
        return login_yn
    }

    // message에 대한 getter 메서드
    fun getMessage(): String {
        return message
    }

     //toString()을 Override 해주지 않으면 객체 주소값을 출력함
    override fun toString(): String {
        return "PostResult{" +
                "login_yn=" + login_yn +
                ", message=" + message + '\'' +
                ", user_info=" + user_info +"}"
    }
}

data class User_Info (

        @SerializedName("u_id")
        val userId: String,
        @SerializedName("u_pwd")
        val userPwd: String,
        @SerializedName("u_name")
        val userName: String,
        @SerializedName("u_nickname")
        val userNickname: String,
        @SerializedName("u_division")
        val userDivision: String,
        @SerializedName("u_gender")
        val userGender: String,
        @SerializedName("u_age")
        val userAge: Int,
        @SerializedName("u_time")
        val userTime: String


): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readInt(),
            parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userId)
        parcel.writeString(userPwd)
        parcel.writeString(userName)
        parcel.writeString(userNickname)
        parcel.writeString(userDivision)
        parcel.writeString(userGender)
        parcel.writeInt(userAge)
        parcel.writeString(userTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User_Info> {
        override fun createFromParcel(parcel: Parcel): User_Info {
            return User_Info(parcel)
        }

        override fun newArray(size: Int): Array<User_Info?> {
            return arrayOfNulls(size)
        }
    }
}