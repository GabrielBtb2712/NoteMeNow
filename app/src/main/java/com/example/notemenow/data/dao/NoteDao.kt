package com.example.notemenow.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.notemenow.data.entity.Notas
import kotlinx.coroutines.flow.Flow

@Dao
 interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY timestamp DESC")
    fun getAllNotes(): Flow<List<Notas>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun  insertNote(note: Notas)
     @Delete
     suspend fun  deleteNote(note: Notas)
     @Update
     suspend fun updateNote(note:Notas)

     @Query("SELECT * FROM notes WHERE id = :noteId")
     suspend fun getNoteById(noteId: Int): Notas?

}