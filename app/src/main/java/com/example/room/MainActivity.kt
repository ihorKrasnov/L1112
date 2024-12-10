package com.example.room

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.views.NoteViewModel
import com.example.room.data.Note
import com.example.room.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NoteItemClickListener
{
    private lateinit var binding: ActivityMainBinding
    private val noteViewModel: NoteViewModel by viewModels {
        NoteViewModelFactory((application as NoteApplication).repository)
    }
    private lateinit var noteAdapter: NoteAdapter
    private var order: String = "DESC"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.addButton.setOnClickListener {
            NewNote(null).show(supportFragmentManager, "newNoteTag")
        }

        binding.sortButton.setOnClickListener {
            order = if (order == "DESC") "ASC" else "DESC"
            noteViewModel.searchNotes(binding.searchView.query.toString(), order)
                .observe(this@MainActivity) {notes ->
                    noteAdapter.updateData(notes)
                }
        }

        noteAdapter = NoteAdapter(emptyList(), this)
        setRecyclerView()
        setupSearch()
    }

    private fun setupSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                noteViewModel.searchNotes(newText, order)
                    .observe(this@MainActivity) { notes ->
                        noteAdapter.updateData(notes)
                    }
                return true
            }
        })
    }

    private fun setRecyclerView() {
        noteViewModel.searchNotes(null, order).observe(this) { notes ->
            if (::noteAdapter.isInitialized) {
                noteAdapter.updateData(notes)
            }
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = noteAdapter
        }
    }

    override fun editTaskItem(taskItem: Note) {
        NewNote(taskItem).show(supportFragmentManager,"newTaskTag")
    }

    override fun deleteItem(taskItem: Note) {
        noteViewModel.delete(taskItem)
    }
}