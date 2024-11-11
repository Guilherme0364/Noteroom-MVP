package com.example.noteroom.data.api

import com.example.noteroom.data.model.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("users")
    fun registerUser(@Body user: User): Call<ResponseBody>

    @POST("/login") // Rota para login
    fun loginUser(@Body user: DTOUser): Call<ResponseBody>

    @GET("/getSubjects")
    fun getSubjects(@Query("userId") userId: Int?): Call<List<Subject>>

    @GET("/getNotebooks")
    fun getNotebooks(@Query("subjectId") subjectId: Int?): Call<ApiResponse>
}
