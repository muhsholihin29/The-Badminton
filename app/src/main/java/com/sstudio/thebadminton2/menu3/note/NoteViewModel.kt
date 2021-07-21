package com.sstudio.thebadminton2.menu3.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sstudio.thebadminton2.Common
import com.sstudio.thebadminton2.core.data.Resource
import com.sstudio.thebadminton2.core.domain.model.Note
import com.sstudio.thebadminton2.core.domain.usecase.TheBadmintonUseCase

class NoteViewModel(private val theBadmintonUseCase: TheBadmintonUseCase): ViewModel() {
    var getNotes: LiveData<Resource<List<Note>>>? = null
        get() {
            if (field == null) {
                field = MutableLiveData()
                field = theBadmintonUseCase.getNote(androidId = Common.androidId).asLiveData()
            }
            return field
        }
        private set

    fun refreshNote(): LiveData<Resource<List<Note>>> {
        return theBadmintonUseCase.getNote(Common.androidId).asLiveData()
    }

    fun insertNote(note: Note): LiveData<Resource<Void>> {
        return theBadmintonUseCase.insertNote(note).asLiveData()
    }

    fun updateNote(note: Note): LiveData<Resource<Void>> {
        return theBadmintonUseCase.updateNote(note).asLiveData()
    }

    fun deleteNote(noteId: Int): LiveData<Resource<Void>> {
        return theBadmintonUseCase.deleteNote(noteId).asLiveData()
    }
}