package com.example.room

import android.app.Application
import com.example.app.view_models.NoteRepository
import com.example.room.data.AppDatabase

class NoteApplication : Application()
{
    private val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { NoteRepository(
        database.noteDao()
    ) }
}
