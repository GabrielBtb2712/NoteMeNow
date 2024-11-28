package com.example.notemenow.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notemenow.data.entity.Notas
import com.example.notemenow.domain.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {

    val allNotes: Flow<List<Notas>> = noteRepository.getAllNotes()
    private val _showDialogEvent = MutableSharedFlow<Boolean>()
    val showDialogEvent = _showDialogEvent.asSharedFlow()

    fun addNote(note: Notas) {
            viewModelScope.launch {
                noteRepository.insertNote(note)
            }
        }


    fun mostrarDialogo() {
        viewModelScope.launch {
            _showDialogEvent.emit(true)
        }
    }


    fun deleteNote(note: Notas) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)

        }
    }

    fun updateNote(note: Notas) {
        viewModelScope.launch {
             noteRepository.deleteNote(note)
        }
    }


}