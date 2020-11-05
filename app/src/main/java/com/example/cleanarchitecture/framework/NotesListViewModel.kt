package com.example.cleanarchitecture.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.core.data.Note
import com.example.core.repository.NoteDataSource
import com.example.core.repository.NoteRepository
import com.example.core.usecase.AddNote
import com.example.core.usecase.GetAllNotes
import com.example.core.usecase.GetNote
import com.example.core.usecase.RemoveNote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesListViewModel(application: Application) : AndroidViewModel(application) {
    val scope = CoroutineScope(Dispatchers.IO)

    val repository = NoteRepository(DBNoteDataSource(application))

    val usecases = Usecases(
        AddNote(repository),
        RemoveNote(repository),
        GetAllNotes(repository),
        GetNote(repository)
    )

    val notesListLiveData = MutableLiveData<List<Note>>()

    fun getAllNotes() {
        scope.launch {
            val notes = usecases.getAllNotes()
            notesListLiveData.postValue(notes)
        }
    }
}