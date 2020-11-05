package com.example.cleanarchitecture.framework

import android.content.Context
import com.example.cleanarchitecture.framework.db.AppDatabase
import com.example.cleanarchitecture.framework.db.NoteEntity
import com.example.core.data.Note
import com.example.core.repository.NoteDataSource

class DBNoteDataSource(context: Context) : NoteDataSource {
    val noteDao = AppDatabase.getInstance(context).noteDao()

    override suspend fun add(note: Note) {
        noteDao.addNoteEntity(NoteEntity.fromNoteToEntity(note))
    }

    override suspend fun get(id: Long): Note? {
        return noteDao.getNotEntity(id)?.toNote()
    }

    override suspend fun getAll(): List<Note> {
        return noteDao.getAllNoteEntity().map {
            it.toNote()
        }
    }

    override suspend fun remove(note: Note) {
        noteDao.deleteNote(NoteEntity.fromNoteToEntity(note))
    }
}