package com.example.cleanarchitecture.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.core.data.Note
import com.example.core.repository.NoteRepository
import com.example.core.usecase.AddNote
import com.example.core.usecase.GetAllNotes
import com.example.core.usecase.GetNote
import com.example.core.usecase.RemoveNote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val scope = CoroutineScope(Dispatchers.IO)
    val repository: NoteRepository = NoteRepository(DBNoteDataSource(application))

    val usecases = Usecases(
        AddNote(repository),
        RemoveNote(repository),
        GetAllNotes(repository),
        GetNote(repository)
    )

    val saveNotesLiveData = MutableLiveData<Boolean>()
    val currentNoteLiveData = MutableLiveData<Note?>()

    fun saveNote(note: Note) {
        scope.launch {
            usecases.addNote(note)
            saveNotesLiveData.postValue(true)
        }
    }

    fun getNote(id: Long) {
        scope.launch {
            val note = usecases.getNote(id)
            currentNoteLiveData.postValue(note)
        }
    }
}