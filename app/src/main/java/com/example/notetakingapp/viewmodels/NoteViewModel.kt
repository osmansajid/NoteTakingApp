package com.example.notetakingapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notetakingapp.modelclasses.Note
import com.example.notetakingapp.repositories.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private var noteRepository = AppRepository(application)
    private var allNotes: LiveData<List<Note>>? = null

    init {
        allNotes = noteRepository.getAllNotes()
    }

    fun deleteAllNotes(){
        viewModelScope.launch(IO) {
            noteRepository.deleteAllNotes()
        }
    }

    fun getAllNote(): LiveData<List<Note>>{
        return allNotes!!
    }
}