package com.example.notetakingapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notetakingapp.modelclasses.Note
import com.example.notetakingapp.repositories.AppRepository
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private var noteRepository: AppRepository? = null
    private var allNotes: LiveData<List<Note>>? = null

    init {
        noteRepository = AppRepository(application)
        allNotes = noteRepository!!.getAllNotes()
    }

    fun insert(note: Note){
        viewModelScope.launch {
            noteRepository!!.insert(note)
        }
    }

    fun update(note: Note){
        viewModelScope.launch {
            noteRepository!!.update(note)
        }
    }

    fun delete(note: Note){
        viewModelScope.launch {
            noteRepository!!.delete(note)
        }
    }

    fun deleteAllNotes(){
        viewModelScope.launch {
            noteRepository!!.deleteAllNotes()
        }
    }

    fun getAllNote(): LiveData<List<Note>>{
        return allNotes!!
    }
}