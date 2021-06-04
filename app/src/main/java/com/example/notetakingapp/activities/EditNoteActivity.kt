package com.example.notetakingapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.notetakingapp.R
import com.example.notetakingapp.constants.GlobalConstants
import com.example.notetakingapp.databinding.ActivityEditNoteBinding
import com.example.notetakingapp.modelclasses.Note
import com.example.notetakingapp.viewmodels.EditNoteViewModel

class EditNoteActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditNoteBinding
    private var viewModel: EditNoteViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewTitle.text = intent.getStringExtra(GlobalConstants.EXTRA_TITLE)
        binding.textViewDescription.text = intent.getStringExtra(GlobalConstants.EXTRA_DESCRIPTION)

        viewModel = ViewModelProvider(this)[EditNoteViewModel::class.java]

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_close)
        title = "Note"
    }

    private fun editNote() {
        val id = intent.getIntExtra(GlobalConstants.EXTRA_ID,-1)
        val title = intent.getStringExtra(GlobalConstants.EXTRA_TITLE)
        val description = intent.getStringExtra(GlobalConstants.EXTRA_DESCRIPTION)
        if(id != -1){
            val intent = Intent(this,AddNoteActivity::class.java)
            intent.putExtra(GlobalConstants.EXTRA_ID,id)
            intent.putExtra(GlobalConstants.EXTRA_TITLE,title)
            intent.putExtra(GlobalConstants.EXTRA_DESCRIPTION,description)
            startActivity(intent)
            finish()
        }
    }

    private fun deleteNote() {
        val id = intent.getIntExtra(GlobalConstants.EXTRA_ID,-1)
        val title = intent.getStringExtra(GlobalConstants.EXTRA_TITLE)
        val description = intent.getStringExtra(GlobalConstants.EXTRA_DESCRIPTION)
        if(id != -1){
            val note = Note(title = title!!,description = description!!)
            note.id = id
            viewModel!!.delete(note)
            Toast.makeText(applicationContext, "Note deleted!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val mMenuInflater =  menuInflater
        mMenuInflater.inflate(R.menu.edit_note_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menuButtonEdit ->{
                editNote()
                true
            }
            R.id.menuButtonDelete ->{
                deleteNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}