package com.example.cleanarchitecture.framework

import com.example.core.usecase.AddNote
import com.example.core.usecase.GetAllNotes
import com.example.core.usecase.GetNote
import com.example.core.usecase.RemoveNote

data class Usecases(

    val addNote: AddNote,
    val removeNote: RemoveNote,
    val getAllNotes: GetAllNotes,
    val getNote: GetNote
)