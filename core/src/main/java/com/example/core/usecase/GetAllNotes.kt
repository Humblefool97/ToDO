package com.example.core.usecase

import com.example.core.repository.NoteRepository

class GetAllNotes(val notesRepository: NoteRepository) {
    suspend operator fun invoke() = notesRepository.getAll()
}