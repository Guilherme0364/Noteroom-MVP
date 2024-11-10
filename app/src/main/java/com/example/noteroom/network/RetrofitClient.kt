package com.example.noteroom.network

import android.content.Context
import com.example.noteroom.data.api.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient(private val context: Context) {
    private val BASE_URL = "http://10.0.2.2:3000/"


    val instance: ApiService by lazy {

        val sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("jwtToken", "") ?: ""

        val client = OkHttpClient.Builder().addInterceptor(AuthInterceptor(token)).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
}