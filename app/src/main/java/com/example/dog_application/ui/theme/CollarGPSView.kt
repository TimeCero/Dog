package com.example.dog_application.ui.theme

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.room.Room
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollarGPSView() {
    val context = LocalContext.current
    var nombrePerro by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var datosCollares by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    val db = Room.databaseBuilder(
        context,
        CollarGPSDatabase::class.java,
        "collar_gps_db"
    ).build()
    val dao = db.collarGPSDao()

    // Layout principal
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Título con temática de perro
                Text(
                    text = "🐾 Gestión de Collares GPS 🐾",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = Color(0xFF3A5A40) // Verde oscuro
                )
                Spacer(modifier = Modifier.height(30.dp))

                // TextFields para ingresar datos
                TextField(
                    value = nombrePerro,
                    onValueChange = { nombrePerro = it },
                    label = { Text("Nombre del Perro") },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFFE6F5D0), // Verde claro
                        focusedIndicatorColor = Color(0xFF3A5A40) // Verde oscuro
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = ubicacion,
                    onValueChange = { ubicacion = it },
                    label = { Text("Ubicación") },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFFE6F5D0),
                        focusedIndicatorColor = Color(0xFF3A5A40)
                    )
                )

                Spacer(modifier = Modifier.weight(1f))

                // Botones con diseño limpio
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                val collar = CollarGPS(nombrePerro = nombrePerro, ubicacion = ubicacion)
                                dao.insert(collar)
                                nombrePerro = ""
                                ubicacion = ""
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3A5A40)) // Verde oscuro
                    ) {
                        Text("Agregar Collar")
                    }

                    Button(
                        onClick = {
                            coroutineScope.launch {
                                val data = dao.getAll().joinToString("\n") {
                                    "${it.nombrePerro}: ${it.ubicacion}"
                                }
                                datosCollares = data
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3A5A40))
                    ) {
                        Text("Listar Collares")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Muestra los datos de los collares
                Text(
                    text = datosCollares,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    color = Color(0xFF3A5A40)
                )
            }
        }
    )
}
