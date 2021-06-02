package com.example.notetakingapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.notetakingapp.R
import com.example.notetakingapp.viewmodels.NoteViewModel

class MainActivity : AppCompatActivity() {
    var noteViewModel: NoteViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        noteViewModel!!.getAllNote().observe(this){
            Toast.makeText(this,"onChanged",Toast.LENGTH_SHORT).show()
        }
    }
}