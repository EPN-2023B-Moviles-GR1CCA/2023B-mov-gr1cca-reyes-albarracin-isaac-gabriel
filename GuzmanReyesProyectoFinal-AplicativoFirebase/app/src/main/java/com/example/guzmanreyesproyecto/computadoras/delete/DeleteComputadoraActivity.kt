package com.example.guzmanreyesproyecto.computadoras.delete

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.guzmanreyesproyecto.R
import com.example.guzmanreyesproyecto.models.Computador
import com.example.guzmanreyesproyecto.service.CRUDService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeleteComputadoraActivity : ComponentActivity() {
    private lateinit var spinnerComputadoras: Spinner
    private lateinit var botonEliminar: Button
    private lateinit var crudService: CRUDService
    private var computadoras: List<Computador> = listOf()
    private var computadoraSeleccionada: Computador? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.computadoras_delete_activity)

        spinnerComputadoras = findViewById(R.id.spinnerComputadoras)
        botonEliminar = findViewById(R.id.botonEliminar)
        crudService = CRUDService(this)

        cargarComputadoras()
        configurarSpinnerComputadoras()
    }

    private fun cargarComputadoras() {
        CoroutineScope(Dispatchers.Main).launch {
            computadoras = crudService.leerComputadores()
            val adapter = ArrayAdapter(this@DeleteComputadoraActivity, android.R.layout.simple_spinner_item, computadoras.map { it.modelo })
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerComputadoras.adapter = adapter
        }
    }

    private fun configurarSpinnerComputadoras() {
        spinnerComputadoras.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                computadoraSeleccionada = computadoras[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                computadoraSeleccionada = null
            }
        }
    }

    fun onEliminarComputadoraClick(view: View) {
        computadoraSeleccionada?.let {
            try {
                crudService.eliminarComputador(it.modelo)
                Toast.makeText(this, "Computadora eliminada con éxito", Toast.LENGTH_SHORT).show()
                cargarComputadoras()
            } catch (e: Exception) {
                Toast.makeText(this, "Error al eliminar la computadora", Toast.LENGTH_SHORT).show()
            }
        } ?: Toast.makeText(this, "Por favor, seleccione una computadora", Toast.LENGTH_SHORT).show()
    }

}