package com.example.notetakingapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notetakingapp.R
import com.example.notetakingapp.adapters.NoteAdapter
import com.example.notetakingapp.adapters.NoteAdapter.OnItemClickListener
import com.example.notetakingapp.constants.GlobalConstants
import com.example.notetakingapp.databinding.ActivityMainBinding
import com.example.notetakingapp.modelclasses.Note
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

        ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                noteViewModel!!.delete(adapter.getNote(viewHolder.adapterPosition))
                Toast.makeText(applicationContext,"Note deleted!",Toast.LENGTH_SHORT).show()
            }

        }).attachToRecyclerView(binding.recyclerViewNotes)

        adapter.setListener(object : OnItemClickListener {
            override fun onClick(note: Note) {
                val intent = Intent(this@MainActivity,EditNoteActivity::class.java)
                intent.putExtra(GlobalConstants.EXTRA_ID, note.id)
                intent.putExtra(GlobalConstants.EXTRA_TITLE, note.title)
                intent.putExtra(GlobalConstants.EXTRA_DESCRIPTION, note.description)
                startActivity(intent)
            }
        })
    }


    private fun showDeleteOrCancelDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Delete all notes")
        alertDialogBuilder.setMessage("Do you want to delete all notes?")
        alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
            noteViewModel!!.deleteAllNotes()
            Toast.makeText(applicationContext, "Note deleted!", Toast.LENGTH_SHORT).show()
        }
        alertDialogBuilder.setNegativeButton("Cancel"){_, _ ->

        }
        alertDialogBuilder.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val mMenuInflater = menuInflater
        mMenuInflater.inflate(R.menu.delete_note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menuDeleteAll->{
                showDeleteOrCancelDialog()
                true
            }
            else-> super.onOptionsItemSelected(item)
        }
    }

}