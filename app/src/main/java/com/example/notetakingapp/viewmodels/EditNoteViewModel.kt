package com.example.notetakingapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.notetakingapp.modelclasses.Note
import com.example.notetakingapp.repositories.AppRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class EditNoteViewModel(application: Application) : AndroidViewModel(application) {
    private val noteRepository = AppRepository(application)

    fun delete(note: Note){
        viewModelScope.launch(IO) {
            noteRepository.delete(note)
        }
    }
}