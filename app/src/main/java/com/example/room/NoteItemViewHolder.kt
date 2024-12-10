package com.example.room

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.room.data.Note
import com.example.room.databinding.NoteItemBinding

class NoteItemViewHolder(
    private val context: Context,
    private val binding: NoteItemBinding,
    private val onClick: NoteItemClickListener) : RecyclerView.ViewHolder(binding.root)
{
    fun bindTaskItem(item: Note)
    {
        binding.title.text = item.title
        binding.text.text = item.text
        binding.createAt.text = item.createdAt

        binding.taskCellContainer.setOnClickListener{
            onClick.editTaskItem(item)
        }

        binding.deleteButton.setOnClickListener {
            onClick.deleteItem(item)
        }
    }
}
