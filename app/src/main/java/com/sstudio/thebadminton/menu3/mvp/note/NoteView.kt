package com.sstudio.thebadminton.menu3.mvp.note

import com.sstudio.thebadminton.model.Note

interface NoteView {
    fun showNote(comment: List<Note>)
    fun refreshNote(title: String, note: String)
    fun toast(text: String)
    fun showDialog()
    fun dismissDialog()
}