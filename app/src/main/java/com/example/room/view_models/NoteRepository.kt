package com.example.app.view_models

import androidx.lifecycle.LiveData
import com.example.room.data.Note
import com.example.room.data.NoteApi
import com.example.room.data.NoteDao

class NoteRepository(private val noteDao: NoteDao, private val noteApi: NoteApi? = null) {

    val allNotes: LiveData<List<Note>> = noteDao.getAllNotesDesc()

    fun insert(note: Note) {
        noteDao.insert(note)
    }

    fun update(note: Note) {
        noteDao.update(note)
    }

    fun delete(note: Note) {
        noteDao.delete(note)
    }

    fun searchNotes(query: String?, sort: String): LiveData<List<Note>> {
        if (query.isNullOrEmpty())
            return allNotes(sort)
        else
            return filterNotes(query, sort)
    }

    private fun allNotes(sort: String): LiveData<List<Note>> {
        if (sort == "DESC")
            return noteDao.getAllNotesDesc()
        else
            return noteDao.getAllNotesAsc()
    }

    private fun filterNotes(query: String, sort: String):  LiveData<List<Note>> {
        if (sort == "DESC")
            return noteDao.searchNotesDesc(query)
        else
            return noteDao.searchNotesAsc(query)
    }
}