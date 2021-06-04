package com.example.notetakingapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.notetakingapp.modelclasses.Note
import com.example.notetakingapp.repositories.AppRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class AddNoteViewModel(application: Application) : AndroidViewModel(application) {
    private var noteRepository : AppRepository = AppRepository(application)

    fun insert(note: Note) {
        viewModelScope.launch(IO) {
            noteRepository.insert(note)
        }
    }

    fun update(note: Note){
        viewModelScope.launch(IO) {
            noteRepository.update(note)
        }
    }
}