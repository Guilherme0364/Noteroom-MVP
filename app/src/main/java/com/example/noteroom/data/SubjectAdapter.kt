package com.example.noteroom.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noteroom.R
import com.example.noteroom.data.model.Subject

class SubjectAdapter(private val subjects: List<Subject>) : RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>() {

    class SubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val subjectName: TextView = itemView.findViewById(R.id.subjectName)
        val notebookIcon: ImageView = itemView.findViewById(R.id.notebookIcon)
        val editIcon: ImageView = itemView.findViewById(R.id.editIcon)
        // Adicionar mais views, caso queira usar a descrição ou cor
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_subject, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        val subject = subjects[position]
        holder.subjectName.text = subject.name

        // Você pode adicionar outras configurações, como usar o 'color' para definir o fundo ou o texto
        // Exemplo: Usar a cor da disciplina para personalizar a exibição
        // holder.itemView.setBackgroundColor(Color.parseColor(subject.color))

        // Configurações adicionais, como clique no editIcon, podem ser colocadas aqui
    }

    override fun getItemCount() = subjects.size
}
