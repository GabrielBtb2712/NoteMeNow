package com.example.notemenow.presentation.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notemenow.R
import com.example.notemenow.data.entity.Notas
import com.example.notemenow.presentation.viewModel.NoteViewModel
import com.example.notemenow.presentation.views.personalizationNotes.ColorTemaNotas

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNoteScreen(
    viewModel: NoteViewModel,
    navController: NavController
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    //val note = viewModel.updateNote(noteId)


    Scaffold { padding ->
        NoteCanvas(
            title = title,
            onTitleChange = { title = it },
            content = content,
            onContentChange = { content = it },
            onSave = {
                if (title.isNotEmpty() && content.isNotEmpty()) {
                    viewModel.addNote(Notas(title = title, content = content))
                    navController.navigate("home")
                } else {

                    navController.navigate("home")
                }
            },
            modifier = Modifier
                .padding(padding)
                .fillMaxSize() // Ocupa toda la pantalla
        )
    }
}

@Composable
fun NoteCanvas(
    title: String,
    onTitleChange: (String) -> Unit,
    content: String,
    onContentChange: (String) -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(Color(0xFFFCEBBE)) // Color de fondo principal
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Bordes del Canvas
            drawRoundRect(
                color = Color.Gray,
                cornerRadius = CornerRadius(16.dp.toPx(), 16.dp.toPx()),
                style = Stroke(width = 5f)
            )
        }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f) // Deja espacio para el botón al final
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(), // Asegura que los elementos se distribuyan correctamente
                    horizontalArrangement = Arrangement.SpaceBetween // Mantiene espacio entre los botones
                ) {
                    IconButton(
                        onClick = onSave,
                        modifier = Modifier.size(40.dp)

                    ) {
                        Icon(
                            painter = painterResource(R.drawable.save),
                            contentDescription = "Guardar",
                            tint = Color.Black
                        )
                    }
                    // Llama al menú desplegable
                    ColorTemaNotas()
                }


                Spacer(modifier = Modifier.height(10.dp))

                // Campo de título
                Text(
                    text = "Título",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                BasicTextField(
                    value = title,
                    onValueChange = onTitleChange,
                    textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFFCEBBE), shape = RoundedCornerShape(8.dp))
                        .padding(8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Campo de contenido
                Text(
                    text = "Contenido",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                BasicTextField(
                    value = content,
                    onValueChange = onContentChange,
                    textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f) // Ajusta dinámicamente según el espacio disponible
                        .background(Color(0xFFFCEBBE), shape = RoundedCornerShape(8.dp))
                        .padding(8.dp)
                )
            }


        }
    }
}



//
//
//@Composable
//fun menuTemaColoresNotas() {
//    var expanded by remember { mutableStateOf(false) } // Controla si el menú está abierto
//    val items = listOf("Opción 1", "Opción 2", "Opción 3") // Opciones del menú
//    var selectedOption by remember { mutableStateOf("") } // Opción seleccionada
//
//    // Usa un Box para posicionar el menú en relación al botón
//    Box {
//        IconButton(onClick = { expanded = !expanded }) { // Botón para desplegar el menú
//            Icon(
//                painter = painterResource(R.drawable.paletacolores),
//                contentDescription = "Abrir menú",
//                tint = Color.Black
//            )
//        }
//
//        // Ajustar la posición del menú desplegable
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false }
//        ) {
//            items.forEach { item ->
//                DropdownMenuItem(
//                    onClick = {
//                        selectedOption = item
//                        expanded = false // Cierra el menú tras seleccionar una opción
//                    },
//                    text = { Text(text = item) }
//                )
//            }
//        }
//    }
//}
//
//
//
//





