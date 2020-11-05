package com.example.cleanarchitecture.framework.di

import android.app.Application
import com.example.cleanarchitecture.framework.DBNoteDataSource
import com.example.core.repository.NoteRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun  providesRepository(app:Application) = NoteRepository(DBNoteDataSource(app))
}