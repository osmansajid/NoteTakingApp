package com.example.notetakingapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notetakingapp.adapters.NoteAdapter
import com.example.notetakingapp.databinding.ActivityMainBinding
import com.example.notetakingapp.viewmodels.NoteViewModel

class MainActivity : AppCompatActivity() {
    private var noteViewModel: NoteViewModel? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewNotes.layoutManager = LinearLayoutManager(this)
        val adapter= NoteAdapter()
        binding.recyclerViewNotes.adapter = adapter

        noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        noteViewModel!!.getAllNote().observe(this){
            adapter.setNotes(it)
        }

        binding.buttonAddNotes.setOnClickListener {
            val intent = Intent(this,AddNoteActivity::class.java)
            startActivity(intent)
        }
    }
}