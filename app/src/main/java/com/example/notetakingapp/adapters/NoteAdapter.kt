package com.example.notetakingapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notetakingapp.R
import com.example.notetakingapp.modelclasses.Note

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteHolder>() {
    private var allNotes: List<Note> = arrayListOf()

    class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        var textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return NoteHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val noteItem = allNotes[position]
        holder.textViewTitle.text = noteItem.title
        holder.textViewDescription.text = noteItem.description
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun setNotes(notes: List<Note>){
        this.allNotes = notes
        notifyDataSetChanged()
    }
}