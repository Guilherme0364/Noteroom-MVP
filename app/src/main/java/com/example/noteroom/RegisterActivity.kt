package com.example.noteroom.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.noteroom.R
import com.example.noteroom.data.database.SingletonDatabase
import com.example.noteroom.data.model.User
import java.sql.PreparedStatement

class RegisterActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inicializando os campos de entrada
        nameEditText = findViewById(R.id.editTextName)
        phoneEditText = findViewById(R.id.editTextPhone)
        emailEditText = findViewById(R.id.editTextEmail)
        passwordEditText = findViewById(R.id.editTextPassword)
        registerButton = findViewById(R.id.buttonRegister)

        // Configurando o clique do botão de registro
        registerButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (name.isNotBlank() && email.isNotBlank() && password.isNotBlank() && phone.isNotBlank()) {
                val user = User(name = name, phone = phone, email = email, password = password)
                registerUser(user)
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerUser(user: User) {
        val connection = SingletonDatabase.getConnection()
        if (connection != null) {
            try {
                val sql = "INSERT INTO users (name, phone, email, password) VALUES (?, ?, ?, ?)"
                val statement: PreparedStatement = connection.prepareStatement(sql).apply {
                    setString(1, user.name)
                    setString(2, user.phone)
                    setString(3, user.email)
                    setString(4, user.password)
                }

                val rowsInserted = statement.executeUpdate()
                if (rowsInserted > 0) {
                    Toast.makeText(this, "Usuário registrado com sucesso!", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Falha ao registrar usuário", Toast.LENGTH_SHORT).show()
                }
                statement.close()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Erro: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Erro na conexão com o banco de dados", Toast.LENGTH_SHORT).show()
        }
    }

    // Ciclo de vida da Activity
    override fun onStart() {
        super.onStart()
        // Qualquer lógica necessária para o onStart
    }

    override fun onResume() {
        super.onResume()
        // Qualquer lógica necessária para o onResume
    }

    override fun onPause() {
        super.onPause()
        // Qualquer lógica necessária para o onPause, como salvar o estado
    }

    override fun onStop() {
        super.onStop()
        // Lógica para onStop, se necessária
    }

    override fun onDestroy() {
        super.onDestroy()
        // Fecha a conexão com o banco de dados ao destruir a Activity
        try {
            val connection = SingletonDatabase.closeConnection()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
