package com.example.cleanarchitecture.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitecture.R
import com.example.core.data.Note
import kotlinx.android.synthetic.main.item_note.view.*
import java.text.SimpleDateFormat
import java.util.*

class NotesListAdapter(var notes: List<Note>, val action: ListAction) :
    RecyclerView.Adapter<NotesListAdapter.NoteViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.bind(note)
    }

    fun updateNotes(newNotes: List<Note>) {
        notes = newNotes
        notifyDataSetChanged()
    }


    inner class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val layout = view.noteLayout
        private val noteTitle = view.title
        private val noteContent = view.content
        private val noteDate = view.date

        fun bind(note: Note) {
            with(note) {
                noteTitle.text = title
                noteContent.text = content
                val sdf = SimpleDateFormat("MMM dd, HH:mm:ss")
                val resultDate = Date(updateTime)
                noteDate.text = "Last updated : ${sdf.format(resultDate)}"
                layout.setOnClickListener {
                    action.onClick(id)
                }
            }
        }
    }
}