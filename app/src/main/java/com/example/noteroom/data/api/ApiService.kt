package com.example.noteroom.data.api

import com.example.noteroom.data.model.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("cadastrar")
    fun registerUser(@Body user: User): Call<ResponseBody>
}