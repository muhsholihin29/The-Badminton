package com.sstudio.thebadminton2.menu3.note

import android.app.Dialog
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sstudio.thebadminton2.Common
import com.sstudio.thebadminton2.R
import com.sstudio.thebadminton2.menu3.note.adapter.NoteAdapter
import com.sstudio.thebadminton2.core.data.Resource
import com.sstudio.thebadminton2.core.domain.model.Comment
import com.sstudio.thebadminton2.core.domain.model.Note
import kotlinx.android.synthetic.main.activity_catatan.*
import kotlinx.android.synthetic.main.activity_catatan.progress_bar
import kotlinx.android.synthetic.main.activity_detail_video.*
import kotlinx.android.synthetic.main.dialog_form.*
import org.koin.android.viewmodel.ext.android.viewModel

class NoteActivity : AppCompatActivity() {

    private var noteAdapter = NoteAdapter()
    private val viewModel: NoteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catatan)

        toolbar_catatan.title = "Catatan Latihan"
        setSupportActionBar(toolbar_catatan)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        swipe_refresh.setOnRefreshListener(noteRefresh)

        rv_note.layoutManager = LinearLayoutManager(this)
        rv_note.adapter = noteAdapter
        noteAdapter.notifyDataSetChanged()

        fab_catatan.setOnClickListener {
            formDialog(null, true)
        }

        viewModel.getNotes?.observe(this){ resource ->
            when(resource){
                is Resource.Loading -> {
                    progress_bar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    progress_bar.visibility = View.GONE
                    resource.data?.let {
                        noteAdapter.notes = it
                        noteAdapter.notifyDataSetChanged()
                    }
                }
                is Resource.Error -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(this, "${resource.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            rv_note.layoutManager = GridLayoutManager(this, 2)
        } else {
            rv_note.layoutManager = GridLayoutManager(this, 4)
        }

        noteAdapter.onItemClick = {
            val intent = Intent(this, NoteDetailActivity::class.java)
            intent.putExtra(NoteDetailActivity.EXTRA, it)
            startActivity(intent)
        }

        noteAdapter.onItemLongClick = {
            dialogNoteOption(it)
        }
    }

    private fun dialogNoteOption(note: Note) {
        val dialogItems = arrayOf<CharSequence>("Ubah", "Hapus")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("")
        builder.setItems(dialogItems) { _, i ->
            when (i) {
                0 -> formDialog(note, false)
                1 -> deleteNote(note.id)
            }
        }
        builder.show()
    }

    private fun formDialog(note: Note?, isAdd: Boolean) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_form)

        if (isAdd) {
            dialog.btn_save.text = "Tambah"
            dialog.btn_save.setOnClickListener {
                viewModel.insertNote(
                    Note(
                        title = dialog.et_title.text.toString(),
                        note = dialog.et_note.text.toString(),
                        androidId = Common.androidId
                    )
                ).observe(this) { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            progress_bar.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            progress_bar.visibility = View.GONE
                            refreshNote()
                        }
                        is Resource.Error -> {
                            progress_bar.visibility = View.GONE
                            Toast.makeText(this, "${resource.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                    dialog.dismiss()
                }

            }
        } else { //update
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
                    ).observe(this){resource ->
                        when (resource) {
                            is Resource.Loading -> {
                                progress_bar.visibility = View.VISIBLE
                            }
                            is Resource.Success -> {
                                progress_bar.visibility = View.GONE
                                refreshNote()
                            }
                            is Resource.Error -> {
                                progress_bar.visibility = View.GONE
                                Toast.makeText(this, "${resource.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                        dialog.dismiss()
                    }
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

    private fun deleteNote(id: Int) {
        val dialog = android.app.AlertDialog.Builder(this)
        dialog.setMessage("Apakah anda yakin hapus catatan?")
        dialog.setPositiveButton("Ya") { dialog, which ->
            dialog.dismiss()
            viewModel.deleteNote(id).observe(this) {
                dialog.dismiss()
            }
        }
        dialog.setNegativeButton("Batal") { dialog, _ ->
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun refreshNote(){
        viewModel.refreshNote().observe(this){ resource ->
            when(resource){
                is Resource.Loading -> {
                    progress_bar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    progress_bar.visibility = View.GONE
                    resource.data?.let {
                        noteAdapter.notes = it
                    }
                }
                is Resource.Error -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(this, "${resource.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private val noteRefresh = SwipeRefreshLayout.OnRefreshListener {
        refreshNote()
        swipe_refresh.isRefreshing = false
    }
}
