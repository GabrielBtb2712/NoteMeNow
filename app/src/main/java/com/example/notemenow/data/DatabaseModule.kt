package com.example.notemenow.data

import android.content.Context
import androidx.room.Room
import com.example.notemenow.data.dao.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Este es el componente en el que se instalarán las dependencias
object DatabaseModule {

    // Método para proveer la base de datos NoteDatabase
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NoteDatabase {
        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "note_database"
        ).fallbackToDestructiveMigration() // Manejo de migraciones
            .build()
    }

    // Método para proveer el DAO de notas
    @Provides
    fun provideNoteDao(database: NoteDatabase): NoteDao {
        return database.noteDao()
    }
}
