package com.example.notetakingapp.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.notetakingapp.dao.NoteDao
import com.example.notetakingapp.database.NoteDataBase
import com.example.notetakingapp.modelclasses.Note

class AppRepository(application: Application){
    private var noteDao: NoteDao? = null
    private var allNotes: LiveData<List<Note>>? = null

    init {
        noteDao = NoteDataBase.getInstance(application).noteDao()
        allNotes = noteDao!!.getAllNotes()
    }

    @Suppress("RedundantSuspendModifier")
    suspend fun insert(note: Note){
        noteDao!!.insert(note)
    }

    @Suppress("RedundantSuspendModifier")
    suspend fun update(note: Note){
        noteDao!!.update(note)
    }

    @Suppress("RedundantSuspendModifier")
    suspend fun delete(note: Note){
        noteDao!!.delete(note)
    }

    @Suppress("RedundantSuspendModifier")
    suspend fun deleteAllNotes(){
        noteDao!!.deleteAllNotes()
    }

    fun getAllNotes(): LiveData<List<Note>>{
        return allNotes!!
    }
}