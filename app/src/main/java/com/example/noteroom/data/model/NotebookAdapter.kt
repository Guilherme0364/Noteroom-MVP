package com.example.noteroom.data.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noteroom.R

// NotebookItem.kt - Definir a estrutura do item
data class NotebookItem(
    val id: Int,
    val name: String,
    val content: String,
    val subject_id: Int
)

// NotebookAdapter.kt - O Adapter do RecyclerView
class NotebookAdapter(private val itemList: List<NotebookItem>) : RecyclerView.Adapter<NotebookAdapter.NotebookViewHolder>() {

    // ViewHolder para os itens do RecyclerView
    class NotebookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.subjectName)
        val editIcon: ImageView = view.findViewById(R.id.editIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotebookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notebook, parent, false)
        return NotebookViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotebookViewHolder, position: Int) {
        val item = itemList[position]
        holder.nameTextView.text = item.name
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
