package com.example.guzmanreyesproyecto.marca.update

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import com.example.guzmanreyesproyecto.R
import com.example.guzmanreyesproyecto.models.MarcaComputador
import com.example.guzmanreyesproyecto.service.CRUDService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

class UpdateSingleMarcaActivity : ComponentActivity() {
    private lateinit var editTextNombre: EditText
    private lateinit var editTextPaisOrigen: EditText
    private lateinit var buttonActualizar: Button
    private lateinit var anoFundacion: EditText
    private val crudService = CRUDService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.marca_update_single)

        editTextNombre = findViewById(R.id.editTextNombre)
        editTextPaisOrigen = findViewById(R.id.editTextPaisOrigen)
        anoFundacion = findViewById(R.id.editTextFundacion)
        buttonActualizar = findViewById(R.id.buttonActualizar)

        val nombreMarca = intent.getStringExtra("nombreMarca")
        cargarDatosMarca(nombreMarca)

        buttonActualizar.setOnClickListener {
            val nuevaMarca = MarcaComputador(
                nombre = editTextNombre.text.toString(),
                paisOrigen = editTextPaisOrigen.text.toString(),
                añoFundacion = anoFundacion.text.toString().toIntOrNull() ?: 0,
                esIndependiente = true,
                fechaRegistro = LocalDate.now(),
            )
            crudService.actualizarMarca(nombreMarca ?: "", nuevaMarca)
            finish()
        }
    }

    private fun cargarDatosMarca(nombreMarca: String?) {
        nombreMarca?.let { nombre ->
            CoroutineScope(Dispatchers.Main).launch {
                val marcas = crudService.leerMarcas() // Asume que leerMarcas ahora es una suspending function
                val marca = marcas.find { it.nombre == nombre }
                marca?.let {
                    editTextNombre.setText(marca.nombre)
                    editTextPaisOrigen.setText(marca.paisOrigen)
                    anoFundacion.setText(marca.añoFundacion.toString())
                }
            }
        }
    }
}