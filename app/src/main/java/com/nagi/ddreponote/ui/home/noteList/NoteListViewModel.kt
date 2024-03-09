package com.nagi.ddreponote.ui.home.noteList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nagi.ddreponote.data.AppDatabase
import com.nagi.ddreponote.data.NoteList.Note
import com.nagi.ddreponote.data.NoteList.NoteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteListViewModel : ViewModel() {
    private var _noteData = MutableLiveData<List<Note>>()
    var noteData: LiveData<List<Note>> = _noteData
    private val database : NoteDao by lazy {
        AppDatabase.getInstance().noteDao()
    }
    init {
        getNoteData()
    }
    fun refreshNoteData() {
        getNoteData()
    }
    private fun getNoteData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dbNoteData = database.getAll()
                _noteData.postValue(dbNoteData)
            }
        }
    }

}