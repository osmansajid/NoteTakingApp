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
    private var listener: OnItemClickListener? = null

    inner class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        var textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)

        init {
            itemView.setOnClickListener{
                if(listener != null && adapterPosition != RecyclerView.NO_POSITION){
                    listener!!.onClick(allNotes[adapterPosition])
                }
            }
        }
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

    fun getNote(pos: Int): Note{
        return allNotes[pos]
    }

    interface OnItemClickListener{
        fun onClick(note: Note)
    }

    fun setListener(listener: OnItemClickListener){
        this.listener = listener
    }
}