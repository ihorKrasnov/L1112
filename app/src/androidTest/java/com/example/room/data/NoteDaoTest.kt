package com.example.room.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.room.getOrAwaitValue
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class NoteDaoTest {
    @get:Rule
    var instantClassExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var noteDao: NoteDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        noteDao = database.noteDao()
    }

    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun InserData() = runBlockingTest {
        //Arrange
        val note = Note(1, "Task 1", "Text 1", "2024-11-12", null)

        //Act
        noteDao.insert(note)
        val notes = noteDao.getAllNotesDesc().getOrAwaitValue()

        //Assert
        assertTrue(notes.any {
            it.title == note.title &&
                    it.text == note.text &&
                    it.createdAt == note.createdAt})
    }

    @Test
    fun updateNote() = runBlockingTest {
        // Arrange
        val note = Note(1, "Task 1", "Text 1", "2024-11-12", null)
        noteDao.insert(note)

        // Act
        val updatedNote = Note(
            id = note.id,
            title = "Updated Task",
            text = "Updated Text",
            createdAt = note.createdAt,
            completedDateString = note.completedDateString
        )
        noteDao.update(updatedNote)
        val notes = noteDao.getAllNotesDesc().getOrAwaitValue()

        // Assert
        assertTrue(notes.any { it.title == updatedNote.title && it.text == updatedNote.text })
    }

    @Test
    fun deleteNote() = runBlockingTest {
        // Arrange
        val note = Note(1, "Task 1", "Text 1", "2024-11-12", null)
        noteDao.insert(note)

        // Act
        noteDao.delete(note)
        val notes = noteDao.getAllNotesDesc().getOrAwaitValue()

        // Assert
        assertFalse(notes.any { it.id == note.id })
    }

    @Test
    fun searchNotesDesc() = runBlockingTest {
        // Arrange
        val note1 = Note(1, "Task 1", "Text 1", "2024-11-12", null)
        val note2 = Note(2, "Another Task", "Text 2", "2024-11-12", null)
        noteDao.insert(note1)
        noteDao.insert(note2)

        // Act
        val query = "Task"
        val result = noteDao.searchNotesDesc(query).getOrAwaitValue()

        // Assert
        assertTrue(result.any { it.title.contains(query, ignoreCase = true) })
        assertTrue(result.size == 2)
    }

    @Test
    fun searchNotesAsc() = runBlockingTest {
        // Arrange
        val note1 = Note(1, "Task 1", "Text 1", "2024-11-12", null)
        val note2 = Note(2, "Another Task", "Text 2", "2024-11-12", null)
        noteDao.insert(note1)
        noteDao.insert(note2)

        // Act
        val query = "Text"
        val result = noteDao.searchNotesAsc(query).getOrAwaitValue()

        // Assert
        assertTrue(result.any { it.text.contains(query, ignoreCase = true) })
        assertTrue(result.size == 2)
    }

    @Test
    fun getAllNotesAsc() = runBlockingTest {
        // Arrange
        val note1 = Note(1, "Task 1", "Text 1", "2024-11-12", null)
        val note2 = Note(2, "Task 2", "Text 2", "2024-11-12", null)
        noteDao.insert(note1)
        noteDao.insert(note2)

        // Act
        val notes = noteDao.getAllNotesAsc().getOrAwaitValue()

        // Assert
        assertTrue(notes.first().id < notes.last().id)
    }

    @Test
    fun getAllNotesDesc() = runBlockingTest {
        // Arrange
        val note1 = Note(1, "Task 1", "Text 1", "2024-11-12", null)
        val note2 = Note(2, "Task 2", "Text 2", "2024-11-12", null)
        noteDao.insert(note1)
        noteDao.insert(note2)

        // Act
        val notes = noteDao.getAllNotesDesc().getOrAwaitValue()

        // Assert
        assertTrue(notes.first().id > notes.last().id)
    }
}
