package com.example.noteroom.data.api

import com.example.noteroom.data.model.DTOUser
import com.example.noteroom.data.model.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("users")
    fun registerUser(@Body user: User): Call<ResponseBody>

    @POST("/login") // Rota para login
    fun loginUser(@Body user: DTOUser): Call<ResponseBody>
}
