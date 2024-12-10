package com.example.room.data

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.room.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity(tableName = "notes")
class Note(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "text") var text: String,
    @ColumnInfo(name = "createdAt") var createdAt: String,
    @ColumnInfo(name = "completedDateString") var completedDateString: String?
) {
    private fun completedDate(): LocalDate? = if (completedDateString == null) null
        else LocalDate.parse(completedDateString, dateFormatter)
    fun createdAt(): LocalDate? = LocalDate.parse(createdAt, dateFormatter)

    fun isCompleted() = completedDate() != null
//    fun imageResource(): Int = if(isCompleted()) R.drawable.checked_24 else R.drawable.unchecked_24
    fun imageColor(context: Context): Int = if(isCompleted()) purple(context) else black(context)

    private fun purple(context: Context) = ContextCompat.getColor(context, R.color.purple_500)
    private fun black(context: Context) = ContextCompat.getColor(context, R.color.black)

    companion object {
        val dateFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE
    }
}