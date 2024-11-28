package com.example.notemenow.presentation.views.personalizationNotes


import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import com.example.notemenow.R


data class ColorItem(val color: Color)

@Composable
fun ColorTemaNotas() {
    var expanded by remember { mutableStateOf(false) }
    var selectedColor by remember { mutableStateOf(Color.White) }


    // Lista de colores, ahora usando ColorItem
    val colorList = listOf(
        ColorItem(Color.Red),
        ColorItem(Color.Green),
        ColorItem(Color.Cyan),
        ColorItem(Color.Blue),
        ColorItem(Color.Yellow),
        ColorItem(Color.Magenta),
        ColorItem(Color.Gray),

    )

    Box {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                painter = painterResource(R.drawable.paletacolores),
                contentDescription = "Abrir menú",
                tint = Color.Black
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            colorList.forEach { colorItem ->
                DropdownMenuItem(
                    onClick = {
                        selectedColor = colorItem.color
                        expanded = false // Cierra el menú tras seleccionar una opción
                    },
                    text = {
                        Text(
                            text = "Color",  // Texto de ejemplo
                            style = TextStyle(color = colorItem.color) // Usamos TextStyle para aplicar el color
                        )

                    }


                )
            }
        }
    }

    // Usa selectedColor para aplicar el color al tema de la nota
    // Ejemplo: Text(color = selectedColor) en algún lugar de tu interfaz
}
