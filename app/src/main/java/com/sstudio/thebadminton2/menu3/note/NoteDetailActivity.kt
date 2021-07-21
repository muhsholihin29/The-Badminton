package com.sstudio.thebadminton2.menu3.note

import android.app.Dialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.sstudio.thebadminton2.R
import com.sstudio.thebadminton2.core.domain.model.Note
import kotlinx.android.synthetic.main.activity_catatan_detail.*
import kotlinx.android.synthetic.main.dialog_form.*
import org.koin.android.viewmodel.ext.android.viewModel

class NoteDetailActivity : AppCompatActivity() {

    private val viewModel: NoteViewModel by viewModel()

    fun refreshNote(title: String, note: String) {
        toolbar_note.title = title
        setSupportActionBar(toolbar_note)
        tv_note.text = note
    }

    companion object {
        val EXTRA = "inttentDetail"
    }

    private var note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catatan_detail)

        note = intent.getParcelableExtra(EXTRA)

        toolbar_note.title = note?.title
        setSupportActionBar(toolbar_note)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        tv_note.text = note?.note
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
                note?.id?.let {
                    viewModel.deleteNote(it).observe(this) {

                    }
                }
                true
            }
            R.id.menu_edit -> {
                formDialog(note, false)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun formDialog(note: Note?, isAdd: Boolean) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_form)

            dialog.et_title.setText(note?.title)
            dialog.et_note.setText(note?.note)
            dialog.btn_save.text = "Ubah"
            dialog.btn_save.setOnClickListener {
                note?.let {
                    viewModel.updateNote(
                        Note(
                            id = it.id,
                            title = dialog.et_title.text.toString(),
                            note = dialog.et_note.text.toString()
                        )
                    ).observe(this){
                        dialog.dismiss()
                    }
                }
            }

        dialog.btn_cancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
        val window = dialog.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }
}
