package com.example.noteroom.telas

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.auth0.android.jwt.JWT
import com.example.noteroom.R

class HomeActivity : AppCompatActivity() {

    private var userId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        setContentView(R.layout.activity_main_menu)
        try {
            val token = getToken()
            val jwt = JWT(token!!)
            userId = jwt.getClaim("id").asString()
        } catch (e: Exception) {
            Log.e("JWT_ERROR", "Erro ao decodificar JWT: ${e.message}")
        }

    }

    override fun onStart() {
        super.onStart()
        val viewMaterias = findViewById<ImageButton>(R.id.subjectsButton)
        viewMaterias.setOnClickListener{
            val intent = Intent(this, MateriasActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun getToken(): String? {
        val sharedPreferences: SharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("jwtToken", null)
    }

}