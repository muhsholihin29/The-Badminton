package com.sstudio.thebadminton.menu3.mvp.note

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sstudio.thebadminton.Common
import com.sstudio.thebadminton.R
import com.sstudio.thebadminton.adapter.NoteAdapter
import com.sstudio.thebadminton.model.Note
import kotlinx.android.synthetic.main.activity_catatan.*
import kotlinx.android.synthetic.main.activity_catatan.swipe_refresh
import kotlinx.android.synthetic.main.activity_video.*

class CatatanActivity : AppCompatActivity(), NoteView {
    override fun dismissDialog() {
        pb_note_list.visibility = View.GONE
    }

    override fun showDialog() {
        pb_note_list.visibility = View.VISIBLE
    }

    override fun showNote(notes: List<Note>) {
        noteAdapter.notes = notes
        noteAdapter.notifyDataSetChanged()
        dismissDialog()

    }

    override fun refreshNote(title: String, note: String) {
        showDialog()
        notePresenterImpl.getNote(Common.androidId)
    }

    override fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    private lateinit var noteAdapter: NoteAdapter
    private val notePresenterImpl = NotePresenterImpl(this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catatan)

        toolbar_catatan.title = "Catatan Latihan"
        setSupportActionBar(toolbar_catatan)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        showDialog()
        swipe_refresh.setOnRefreshListener(noteRefresh)
        noteAdapter = NoteAdapter(this)

        rv_note.layoutManager = LinearLayoutManager(this)
        rv_note.adapter = noteAdapter
        noteAdapter.notifyDataSetChanged()

        notePresenterImpl.getNote(Common.androidId)

        fab_catatan.setOnClickListener {
            notePresenterImpl.formDialog(null, true)
        }

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            rv_note.layoutManager = GridLayoutManager(this, 2)
        } else {
            rv_note.layoutManager = GridLayoutManager(this, 4)
        }
    }

    fun noteOnLongClick(note: Note){
        notePresenterImpl.dialogNoteOption(note)
    }


    private val noteRefresh = SwipeRefreshLayout.OnRefreshListener {
        refreshNote("","")
        swipe_refresh.isRefreshing = false
    }

    override fun onResume() {
        super.onResume()
        refreshNote("", "")
    }
}
