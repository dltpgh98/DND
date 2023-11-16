package com.example.retrofit

import com.google.gson.annotations.SerializedName

class PostResult {
    @SerializedName("login_yn")
    private val login_yn = ""

    @SerializedName("message")
    private val message = ""

    // login_yn에 대한 getter 메서드
    fun getLoginYn(): String {
        return login_yn
    }

    // message에 대한 getter 메서드
    fun getMessage(): String {
        return message
    }

    // toString()을 Override 해주지 않으면 객체 주소값을 출력함
    override fun toString(): String {
        return "PostResult{" +
                "login_yn=" + login_yn +
                ", message=" + message + '\'' +
                '}'
    }
}