package com.example.noteroom.data.database

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object SingletonDatabase {
    private const val DB_URL = "jdbc:mysql://<host>:<port>/<database>"  // Atualize com o endereço correto
    private const val DB_USER = "root"
    private const val DB_PASSWORD = "1234"

    private var connection: Connection? = null

    init {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver")
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Synchronized
    fun getConnection(): Connection? {
        if (connection == null || connection!!.isClosed) {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)
        }
        return connection
    }

    fun closeConnection() {
        try {
            connection?.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
}
