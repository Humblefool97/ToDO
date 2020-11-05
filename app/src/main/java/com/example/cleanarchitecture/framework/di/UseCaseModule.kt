package com.example.cleanarchitecture.framework.di

import com.example.cleanarchitecture.framework.Usecases
import com.example.core.repository.NoteRepository
import com.example.core.usecase.AddNote
import com.example.core.usecase.GetAllNotes
import com.example.core.usecase.GetNote
import com.example.core.usecase.RemoveNote
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun providesUseCases(repository: NoteRepository) = Usecases(
        AddNote(repository),
        RemoveNote(repository),
        GetAllNotes(repository),
        GetNote(repository)
    )

}