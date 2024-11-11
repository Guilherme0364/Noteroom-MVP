package com.example.noteroom.telas

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.auth0.android.jwt.JWT
import com.example.noteroom.R
import com.example.noteroom.data.api.ApiService
import com.example.noteroom.data.model.ApiResponse
import com.example.noteroom.data.model.NotebookAdapter
import com.example.noteroom.data.model.NotebookItem
import com.example.noteroom.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AtividadeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val apiService: ApiService by lazy { RetrofitClient(this).instance }
    private lateinit var notebookAdapter: NotebookAdapter
    private val notebookList: MutableList<NotebookItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notebook)

        recyclerView = findViewById(R.id.recyclerView)

        // Configura o layout manager para o RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Configura o adapter
        notebookAdapter = NotebookAdapter(notebookList)
        recyclerView.adapter = notebookAdapter

        // Busca os dados dos cadernos
        fetchNotebookData()
    }

    private fun fetchNotebookData() {

        val sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val token: String? = sharedPreferences.getString("jwtToken", null)


        if (token == null) {
            Toast.makeText(this, "Token inválido ou não encontrado", Toast.LENGTH_SHORT).show()
            return
        }


        val jwt = JWT(token)
        val idUser = jwt.getClaim("id").asInt()

        if (idUser == null) {
            Toast.makeText(this, "Token inválido ou 'id' não encontrado", Toast.LENGTH_SHORT).show()
            return
        }

        apiService.getNotebooks(idUser).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (apiResponse != null && apiResponse.status == "success") {
                        val notebooks = apiResponse.notebooks

                        // Verifica se a lista de notebooks não está vazia
                        if (notebooks.isNotEmpty()) {
                            notebookList.clear()
                            notebookList.addAll(notebooks)
                            notebookAdapter.notifyDataSetChanged()
                        } else {
                            Toast.makeText(this@AtividadeActivity, "Nenhum caderno encontrado", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@AtividadeActivity, "Erro: ${apiResponse?.detail}", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@AtividadeActivity, "Erro ao carregar cadernos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(this@AtividadeActivity, "Falha na comunicação com o servidor: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}