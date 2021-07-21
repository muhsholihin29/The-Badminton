package com.sstudio.thebadminton2.menu3.note.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sstudio.thebadminton2.R
import com.sstudio.thebadminton2.core.domain.model.Note
import kotlinx.android.synthetic.main.item_note.view.*
import java.util.*

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    var notes: List<Note> = ArrayList()
    var onItemLongClick: ((Note) -> Unit)? = null
    var onItemClick: ((Note) -> Unit)? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_note, viewGroup, false)
        )
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]

        holder.txt_title.text = note.title
        holder.txt_note.text = note.note

        holder.cv_note.setOnClickListener {
            onItemClick?.invoke(note)
        }

        holder.cv_note.setOnLongClickListener {
            onItemLongClick?.invoke(note)
            true
        }
    }

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txt_title = itemView.txt_title
        val txt_note = itemView.txt_note
        var cv_note = itemView.cv_item_note
    }
}