package com.example.core.repository

import com.example.core.data.Note

class NoteRepository(val noteDataSource: NoteDataSource) {
    suspend fun add(note:Note) = noteDataSource.add(note)

    suspend fun remove(note: Note) = noteDataSource.remove(note)

    suspend fun  getNote(id:Long) = noteDataSource.get(id)

    suspend fun  getAll () = noteDataSource.getAll()
}