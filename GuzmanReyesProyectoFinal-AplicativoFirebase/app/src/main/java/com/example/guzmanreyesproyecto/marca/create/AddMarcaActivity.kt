package com.example.guzmanreyesproyecto.marca.create

import com.example.guzmanreyesproyecto.service.CRUDService
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import com.example.guzmanreyesproyecto.R
import com.example.guzmanreyesproyecto.models.MarcaComputador
import java.time.LocalDate

class AddMarcaActivity : ComponentActivity() {
    private lateinit var crudService: CRUDService

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.marca_create_activity)

        val editTextNombre = findViewById<EditText>(R.id.editTextNombreMarca)
        val editTextPais = findViewById<EditText>(R.id.editTextPaisOrigen)
        val editTextAnoFundacion = findViewById<EditText>(R.id.editTextAnoFundacion)
        val checkBoxIndependiente = findViewById<CheckBox>(R.id.checkBoxEsIndependiente)
        val buttonAgregar = findViewById<Button>(R.id.buttonAgregarMarca)

        buttonAgregar.setOnClickListener {
            val nombre = editTextNombre.text.toString()
            val pais = editTextPais.text.toString()
            val anoFundacion = editTextAnoFundacion.text.toString().toIntOrNull() ?: 0
            val esIndependiente = checkBoxIndependiente.isChecked

            val nuevaMarca = MarcaComputador(
                nombre = nombre,
                paisOrigen = pais,
                añoFundacion = anoFundacion,
                esIndependiente = esIndependiente,
                fechaRegistro = LocalDate.now()
            )

            try {
                val crudService = CRUDService(this)
                crudService.addMarca(nuevaMarca)
                Toast.makeText(this, "Marca creada con éxito", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Error al crear la marca", Toast.LENGTH_SHORT).show()
            }

        }
    }
}