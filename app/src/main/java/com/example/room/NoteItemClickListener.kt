package com.example.room

import com.example.room.data.Note

interface NoteItemClickListener {
    fun editTaskItem(taskItem: Note)
    fun deleteItem(taskItem: Note)
}
