package com.example.notetakingapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notetakingapp.R
import com.example.notetakingapp.adapters.NoteAdapter
import com.example.notetakingapp.viewmodels.NoteViewModel

class MainActivity : AppCompatActivity() {
    private var noteViewModel: NoteViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewNotes)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter= NoteAdapter()
        recyclerView.adapter = adapter

        noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        noteViewModel!!.getAllNote().observe(this){
            adapter.setNotes(it)
        }
    }
}