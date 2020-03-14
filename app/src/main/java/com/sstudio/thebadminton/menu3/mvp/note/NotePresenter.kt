package com.sstudio.thebadminton.menu3.mvp.note

import com.sstudio.thebadminton.model.Note

interface NotePresenter {
    fun getNote(androidId: String)
    fun addNote(title: String, note: String, androidId: String)
    fun deleteNote(id: String)
    fun updateNote(id: String, title: String, note: String)
    fun formDialog(note: Note?, isAdd: Boolean)
    fun dialogNoteOption(note: Note)
}