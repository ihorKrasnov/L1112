package com.example.room.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("SELECT * FROM notes " +
            "ORDER BY id " +
            "DESC")
    fun getAllNotesDesc(): LiveData<List<Note>>
    @Query("SELECT * FROM notes " +
            "ORDER BY id " +
            "ASC")
    fun getAllNotesAsc(): LiveData<List<Note>>

    @Query("SELECT * FROM notes " +
            "WHERE LOWER(title) LIKE LOWER('%' || :query || '%') OR " +
                "LOWER(text) LIKE LOWER('%' || :query || '%') " +
            "ORDER BY id " +
            "DESC")
    fun searchNotesDesc(query: String): LiveData<List<Note>>
    // - LIKE: Оператор, який дозволяє порівнювати рядки, шукаючи певний шаблон.
    // - '%' || :query || '%': Цей вираз додає символи '%' до введеного запиту,
    //   що означає "будь-яка кількість символів перед і після введеного тексту".
    @Query("SELECT * FROM notes " +
            "WHERE LOWER(title) LIKE LOWER('%' || :query || '%') OR " +
            "LOWER(text) LIKE LOWER('%' || :query || '%') " +
            "ORDER BY id " +
            "ASC")
    fun searchNotesAsc(query: String): LiveData<List<Note>>

}