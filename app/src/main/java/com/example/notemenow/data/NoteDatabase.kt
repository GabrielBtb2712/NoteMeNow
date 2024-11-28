package com.example.notemenow.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notemenow.data.dao.NoteDao
import com.example.notemenow.data.entity.Notas

@Database(entities = [Notas::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

}