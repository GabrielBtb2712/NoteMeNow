package com.example.notemenow.domain

import com.example.notemenow.data.dao.NoteDao
import com.example.notemenow.data.entity.Notas
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao
) {


    fun getAllNotes(): Flow<List<Notas>> = noteDao.getAllNotes()

    suspend fun insertNote(note: Notas) {
        noteDao.insertNote(note)

    }

    suspend fun deleteNote(note: Notas) {
        noteDao.deleteNote(note)
    }

    suspend fun updateNote(note: Notas) {
        noteDao.updateNote(note)

    }



}