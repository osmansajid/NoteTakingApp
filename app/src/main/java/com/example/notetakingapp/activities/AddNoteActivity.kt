package com.example.notetakingapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.notetakingapp.R
import com.example.notetakingapp.databinding.ActivityAddNoteBinding
import com.example.notetakingapp.modelclasses.Note
import com.example.notetakingapp.viewmodels.AddNoteViewModel

class AddNoteActivity : AppCompatActivity() {
    private var viewModel: AddNoteViewModel? = null
    private lateinit var binding: ActivityAddNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[AddNoteViewModel::class.java]

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_close)
        title = "Add Note"
    }

    private fun saveNote() {
        val title = binding.editTextTitle.text.toString()
        val description = binding.editTextDescription.text.toString()
        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Title or Description can not be empty!", Toast.LENGTH_SHORT)
                .show()
            return
        }
        viewModel!!.insert(Note(title = title, description = description))
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val mMenuInflater = menuInflater
        mMenuInflater.inflate(R.menu.add_note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuButtonSave -> {
                saveNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}