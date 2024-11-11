package com.example.noteroom.data.model

data class ApiResponse(
    val status: String,
    val detail: String,
    val notebooks: List<NotebookItem>
)

