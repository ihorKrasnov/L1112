package com.example.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.room.data.Note
import com.example.room.databinding.NoteItemBinding

class NoteAdapter(
    private var noteItems: List<Note>,
    private val onClick: NoteItemClickListener
): RecyclerView.Adapter<NoteItemViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = NoteItemBinding.inflate(from, parent, false)
        return NoteItemViewHolder(parent.context, binding, onClick)
    }

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {
        holder.bindTaskItem(noteItems[position])
    }

    override fun getItemCount(): Int = noteItems.size

    fun updateData(newNotes: List<Note>) {
        noteItems = newNotes
        notifyDataSetChanged()  // Notify the adapter that the data has changed
    }
}
