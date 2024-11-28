package com.example.notemenow.presentation.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.notemenow.presentation.viewModel.NoteViewModel
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notemenow.data.entity.Notas
import com.example.notemenow.presentation.components.Alertas
import com.example.notemenow.presentation.components.CardNota
import com.example.notemenow.presentation.components.myTopBar
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox




@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(viewModel: NoteViewModel, navController: NavController) {
    val notes = viewModel.allNotes.collectAsState(initial = emptyList())
    var isDarkTheme by rememberSaveable { mutableStateOf(false) } // Estado para el tema oscuro
    var showAlert by remember { mutableStateOf(false) }
    var noteTo by remember { mutableStateOf<Notas?>(null) }
    var isWiping by remember { mutableStateOf(false) }


    Scaffold(
            topBar = {
                myTopBar(
                    title = "NoteMeNow",
                    onIconButtonClick = {
                        navController.navigate("addNote")
                    },
                    seleccionarTema = {
                        isDarkTheme = !isDarkTheme // Alternar el tema
                    }
                )

            },
            floatingActionButton = {
                FloatingActionButton(onClick = { navController.navigate("addNote") }) {
                    Text(text = "+")
                }
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier.padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                items(notes.value) { note ->
                    val delete = SwipeAction(
                        icon = rememberVectorPainter(Icons.Default.Delete),
                        background = Color.Red,
                        onSwipe = {
                            noteTo = note // Guarda la nota a eliminar
                            showAlert = true // Muestra el diálogo de confirmación
                            isWiping = true

                        }
                    )

                    val edit = SwipeAction(
                        icon = rememberVectorPainter(Icons.Default.EditNote),
                        background = Color.Green,
                        onSwipe = {
                            noteTo = note
                            navController.navigate("editNote")
                        }
                    )

                    SwipeableActionsBox(
                        endActions = listOf(delete),
                        //    editActions = listOf(edit)
                    ) {
                        CardNota(note = note)
                    }
                }
            }


            Alertas(
                showAlert = showAlert,
                title = "Eliminar Nota",
                content = "¿Estás seguro de que deseas eliminar esta nota?",
                onDismiss = {
                    showAlert = false
                    noteTo?.let { viewModel.deleteNote(note = it) }
                    noteTo = null

                }
            )
        }

}








