package com.example.notemenow.presentation.components


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.notemenow.R
import com.example.notemenow.data.entity.Notas


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun myTopBar(
    title: String,
    onIconButtonClick: () -> Unit,
    seleccionarTema: () -> Unit)
{
    CenterAlignedTopAppBar(
        title = { Text(title) },
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        actions = {
            // Botón de menú
            IconButton(onClick = onIconButtonClick) {
                Icon(
                    painter = painterResource(id = R.drawable.menu),
                    contentDescription = "Menú",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

            // Botón para alternar tema
            IconButton(onClick = seleccionarTema) {
                val icon = if (isSystemInDarkTheme()) {
                    painterResource(id = R.drawable.luz)
                } else {
                    painterResource(id = R.drawable.luz)
                }

                Icon(
                    painter = icon,
                    contentDescription = "Cambiar tema",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    )
}



@Composable
fun CardNota(note: Notas) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp) // Altura adaptada
            .padding(8.dp)

    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Fondo del Canvas
            drawRoundRect(
                color = Color(0xFFFFF4E5), // Color suave para el fondo
                cornerRadius = CornerRadius(16.dp.toPx(), 16.dp.toPx())
            )

            // Bordes
            drawRoundRect(
                color = Color.Gray,
                cornerRadius = CornerRadius(16.dp.toPx(), 16.dp.toPx()),
                style = Stroke(width = 3f)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Título
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                maxLines = 1, // Limita a una línea
                overflow = TextOverflow.Ellipsis // Agrega "..." si es muy largo
            )

            // Contenido
            Text(
                text = note.content,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray,
                maxLines = 2, // Limita a dos líneas
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}



@Composable
fun Alertas(showAlert: Boolean, onDismiss: () -> Unit, title: String, content: String) {

    if (showAlert) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(title) },
            text = { Text(content) },
            confirmButton = {
                Button(onClick = onDismiss)
                { Text("Aceptar") }
            },
            dismissButton = {
                Button(onClick = onDismiss)
                { Text("Cancelar") }
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun cardsAddNotas(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 36.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                label = { Text(text = label) },
                modifier = modifier
                    .focusRequester(focusRequester),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                )
            )
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}