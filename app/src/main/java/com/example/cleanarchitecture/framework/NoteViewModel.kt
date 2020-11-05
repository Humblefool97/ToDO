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

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val scope = CoroutineScope(Dispatchers.IO)

    @Inject
    lateinit var usecases: Usecases

    val saveNotesLiveData = MutableLiveData<Boolean>()
    val currentNoteLiveData = MutableLiveData<Note?>()

    init {
        DaggerViewModelComponent.builder()
            .appModule(AppModule(application))
            .build()
            .inject(this)
    }

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