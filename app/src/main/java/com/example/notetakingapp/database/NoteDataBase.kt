package com.example.notetakingapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notetakingapp.dao.NoteDao
import com.example.notetakingapp.modelclasses.Note

@Database(entities = [Note::class], version = 1)
public abstract class NoteDataBase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object{
        @Volatile
        private var INSTANCE: NoteDataBase? = null

        fun getInstance(context: Context): NoteDataBase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(context,
                    NoteDataBase::class.java,
                    "note_database"
                ).fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}