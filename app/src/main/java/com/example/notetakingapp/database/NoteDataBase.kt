package com.example.notetakingapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.notetakingapp.dao.NoteDao
import com.example.notetakingapp.modelclasses.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

@Database(entities = [Note::class], version = 1)
abstract class NoteDataBase : RoomDatabase() {

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
                    .addCallback(callback)
                    .build()

                INSTANCE = instance
                instance
            }
        }

        private val callback = object : RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                CoroutineScope(IO).launch {
                    populateDb()
                }
            }
        }

        @Suppress("RedundantSuspendModifier")
        suspend fun populateDb(){
            INSTANCE!!.noteDao().insert(Note("First Note","This is your first note!"))
        }
    }
}