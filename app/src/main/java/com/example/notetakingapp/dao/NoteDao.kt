package com.example.notetakingapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notetakingapp.modelclasses.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)

    @Delete
    fun delete(note: Note)

    @Update
    fun update(note: Note)

    @Query("DELETE FROM note_table")
    fun deleteAllNotes()

    @Query("SELECT * FROM note_table")
    fun getAllNotes(): LiveData<List<Note>>
}