package com.sstudio.thebadminton.menu3.mvp.note

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.sstudio.thebadminton.Common
import com.sstudio.thebadminton.R
import com.sstudio.thebadminton.model.Note
import com.sstudio.thebadminton.remote.IUploadAPI
import com.sstudio.thebadminton.remote.RetrofitClient
import kotlinx.android.synthetic.main.dialog_form.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.view.ViewGroup


class NotePresenterImpl(private val context: Context, private val noteView: NoteView): NotePresenter {

    private val apiServices = RetrofitClient.getClient().create(IUploadAPI::class.java)

    
    override fun getNote(androidId: String) {
        val call = apiServices.getNote(androidId)
        var noteList: List<Note>? = null
        call.enqueue(object : Callback<List<Note>> {
            override fun onResponse(call: Call<List<Note>>, response: Response<List<Note>>) {
                noteList = response.body()
                noteList?.let { noteView.showNote(it) }
                noteView.dismissDialog()
            }

            override fun onFailure(call: Call<List<Note>>, t: Throwable?) {
                t?.message?.let { noteView.toast(it) }
                noteView.dismissDialog()
                noteView.dismissDialog()
            }
        })
    }

    override fun addNote(title: String, note: String, androidId: String) {
        Thread(Runnable {
            apiServices.insertNote(title, note, androidId)
                .enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        noteView.toast("Catatan berhasil ditambah")
                        noteView.dismissDialog()
                        noteView.refreshNote("", "")
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        noteView.toast(t.message + "onFailure.insertNote")
                        noteView.dismissDialog()
                    }
                })
        }).start()
    }

    override fun deleteNote(id: String) {
        val dialog = android.app.AlertDialog.Builder(context)
        dialog.setMessage("Apakah anda yakin hapus catatan?")
        dialog.setPositiveButton("Ya") { dialog, which ->
            dialog.dismiss()
            Thread(Runnable {
                apiServices.deleteNote(id)
                    .enqueue(object : Callback<Void> {
                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                            noteView.toast("Catatan berhasil dihapus")
                            noteView.dismissDialog()
                            context.startActivity(Intent(context, CatatanActivity::class.java))
                        }

                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            noteView.toast(t.message + "onFailure.deleteNote")
                            noteView.dismissDialog()
                        }
                    })
            }).start()
        }
        dialog.setNegativeButton(
            "Batal"
        ) { dialog, _ ->
            dialog.dismiss()
            return@setNegativeButton
        }
        dialog.show()
    }

    override fun updateNote(id: String, title: String, note: String) {

        Thread(Runnable {
//            apiServices.updateNote("menu1", "zz", "zz", "zz")
            apiServices.updateNote(id, title, note)
                .enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        noteView.refreshNote(title, note)
                        noteView.toast("Catatan berhasil diubah")
                        noteView.dismissDialog()
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        noteView.toast(t.message + "onFailure.insertNote")
                        noteView.dismissDialog()
                    }
                })
        }).start()
    }

    override fun formDialog(note: Note?, isAdd: Boolean) {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_form)
        Log.d("taggNote", Common.androidId)
        if (isAdd) {
            dialog.btn_save.text = "Tambah"
            dialog.btn_save.setOnClickListener {
                addNote(
                    dialog.et_title.text.toString(),
                    dialog.et_note.text.toString(),
                    Common.androidId)
                dialog.dismiss()
            }
        } else { //update
            dialog.et_title.setText(note?.title)
            dialog.et_note.setText(note?.note)
            dialog.btn_save.text = "Ubah"
            dialog.btn_save.setOnClickListener {
                note?.let {
                    updateNote(
                        it.id,
                        dialog.et_title.text.toString(),
                        dialog.et_note.text.toString()
                    )
                }
                dialog.dismiss()
            }
        }

        dialog.btn_cancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
        val window = dialog.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun dialogNoteOption(note: Note) {
        val dialogItems = arrayOf<CharSequence>("Ubah","Hapus")
        val builder = AlertDialog.Builder(context)
        builder.setTitle("")
        builder.setItems(dialogItems) { _, i ->
            when (i){
                0 -> formDialog(note, false)
                1 -> deleteNote(note.id)
            }
        }
        builder.show()
    }
}