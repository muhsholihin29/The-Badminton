package com.sstudio.thebadminton.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sstudio.thebadminton.R
import com.sstudio.thebadminton.menu3.mvp.note.CatatanActivity
import com.sstudio.thebadminton.menu3.mvp.note.CatatanDetailActivity
import com.sstudio.thebadminton.model.Note
import kotlinx.android.synthetic.main.item_note.view.*
import java.util.*

class NoteAdapter(private val context: Context) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    var notes: List<Note> = ArrayList()

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
            val intent = Intent(context, CatatanDetailActivity::class.java)
            intent.putExtra(CatatanDetailActivity.EXTRA, note)
            context.startActivity(intent)
        }

        holder.cv_note.setOnLongClickListener {
                (context as CatatanActivity).noteOnLongClick(note)
            true
        }
    }

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txt_title = itemView.txt_title
        val txt_note = itemView.txt_note
        var cv_note = itemView.cv_item_note
    }
}