package com.sstudio.thebadminton.menu3.mvp.note

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sstudio.thebadminton.Common
import com.sstudio.thebadminton.R
import com.sstudio.thebadminton.model.Note
import kotlinx.android.synthetic.main.activity_catatan_detail.*

class CatatanDetailActivity : AppCompatActivity(), NoteView {
    override fun showNote(comment: List<Note>) {

    }

    override fun refreshNote(title: String, note: String) {
        toolbar_note.title = title
        setSupportActionBar(toolbar_note)
        tv_note.text = note
    }

    override fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun showDialog() {

    }

    override fun dismissDialog() {

    }

    companion object {
        val EXTRA = "inttentDetail"
    }

    private lateinit var note: Note
    private val notePresenterImpl = NotePresenterImpl(this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catatan_detail)

        note = intent.getParcelableExtra(EXTRA)

        toolbar_note.title = note.title
        setSupportActionBar(toolbar_note)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        tv_note.text = note.note
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_video, menu)
        menu.findItem(R.id.menu_delete).isVisible = true
        menu.findItem(R.id.menu_save).isVisible = false
        menu.findItem(R.id.menu_edit).isVisible = true
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_delete -> {
                notePresenterImpl.deleteNote(note.id)
                true
            }
            R.id.menu_edit -> {
                notePresenterImpl.formDialog(note, false)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
