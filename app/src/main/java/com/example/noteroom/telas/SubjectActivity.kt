package com.example.noteroom.telas

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.auth0.android.jwt.JWT
import com.example.noteroom.R
import com.example.noteroom.data.SubjectAdapter
import com.example.noteroom.data.api.ApiService
import com.example.noteroom.data.model.Subject
import com.example.noteroom.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubjectActivity : AppCompatActivity() {

    private lateinit var subjectRecyclerView: RecyclerView
    private val apiService: ApiService by lazy { RetrofitClient(this).instance }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subject)

        subjectRecyclerView = findViewById(R.id.subjectRecyclerView)
        subjectRecyclerView.layoutManager = LinearLayoutManager(this)

        fetchSubjectsFromApi()

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

    private fun fetchSubjectsFromApi() {
        val sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val token: String? = sharedPreferences.getString("jwtToken", null)

        val jwt = JWT(token.toString())
        val idUser = jwt.getClaim("id").asInt()

        apiService.getSubjects(idUser).enqueue(object : Callback<List<Subject>> {
            override fun onResponse(call: Call<List<Subject>>, response: Response<List<Subject>>) {
                if (response.isSuccessful) {
                    val subjects = response.body() ?: emptyList()
                    // Configurar o RecyclerView com a lista de disciplinas
                    subjectRecyclerView.adapter = SubjectAdapter(subjects)
                } else {
                    Toast.makeText(this@SubjectActivity, "Erro ao carregar as disciplinas", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Subject>>, t: Throwable) {
                Toast.makeText(this@SubjectActivity, "Falha na comunicação com o servidor", Toast.LENGTH_SHORT).show()
            }
        })
    }

}