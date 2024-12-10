package com.example.room

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.app.views.NoteViewModel
import com.example.room.data.Note
import com.example.room.databinding.FragmentNewNoteBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalDate
import java.time.LocalTime


class NewNote(var note: Note?) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentNewNoteBinding
    private lateinit var noteViewModel: NoteViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()

        if (note != null)
        {
            binding.taskTitle.text = "Edit Note"
            val editable = Editable.Factory.getInstance()
            binding.name.text = editable.newEditable(note!!.title)
            binding.desc.text = editable.newEditable(note!!.text)
        }
        else
        {
            binding.taskTitle.text = "New Task"
        }

        noteViewModel = ViewModelProvider(activity).get(NoteViewModel::class.java)
        binding.saveButton.setOnClickListener {
            saveAction()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewNoteBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun saveAction()
    {
        val title = binding.name.text.toString()
        val text = binding.desc.text.toString()
        if(note == null)
        {
            val newTask = Note(0, title, text, LocalDate.now().toString(),null)
            noteViewModel.insert(newTask)
        }
        else
        {
            note!!.title = title
            note!!.text = text

            noteViewModel.update(note!!)
        }
        binding.name.setText("")
        binding.desc.setText("")

        dismiss()
    }
}