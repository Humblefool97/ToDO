package com.example.cleanarchitecture.framework.di

import com.example.cleanarchitecture.framework.NoteViewModel
import com.example.cleanarchitecture.framework.NotesListViewModel
import dagger.Component

@Component(modules = [AppModule::class, RepositoryModule::class, UseCaseModule::class])
interface ViewModelComponent {
    fun inject(noteViewModel: NoteViewModel)
    fun inject(notesListViewModel: NotesListViewModel)
}