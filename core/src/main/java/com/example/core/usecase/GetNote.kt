package com.example.core.usecase

import com.example.core.repository.NoteRepository

class GetNote(val notesRepository: NoteRepository) {
    suspend operator fun invoke(id: Long) = notesRepository.getNote(id)
}