package com.example.cleanarchitecture.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.cleanarchitecture.framework.di.AppModule
import com.example.cleanarchitecture.framework.di.DaggerViewModelComponent
import com.example.core.data.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotesListViewModel(application: Application) : AndroidViewModel(application) {
    val scope = CoroutineScope(Dispatchers.IO)

    @Inject
    lateinit var usecases: Usecases

    val notesListLiveData = MutableLiveData<List<Note>>()

    init {
        DaggerViewModelComponent.builder()
            .appModule(AppModule(application))
            .build()
            .inject(this)
    }

    fun getAllNotes() {
        scope.launch {
            val notes = usecases.getAllNotes()
            notesListLiveData.postValue(notes)
        }
    }
}