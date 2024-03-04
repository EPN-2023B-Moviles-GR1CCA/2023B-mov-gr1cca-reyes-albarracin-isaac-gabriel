package com.example.guzmanreyesproyecto.computadoras.update

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.guzmanreyesproyecto.R
import com.example.guzmanreyesproyecto.models.Computador
import com.example.guzmanreyesproyecto.models.TipoComputador
import com.example.guzmanreyesproyecto.service.CRUDService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateComputadorasActivity : ComponentActivity() {
    private lateinit var spinnerComputadoras: Spinner
    private lateinit var editTextModelo: EditText
    private lateinit var editTextPrecio: EditText
    private lateinit var spinnerTipo: Spinner
    private lateinit var editTextAnoLanzamiento: EditText
    private lateinit var checkBoxEnProduccion: CheckBox
    private lateinit var botonActualizar: Button

    private lateinit var crudService: CRUDService
    private var computadoras: List<Computador> = listOf()
    private var computadoraSeleccionada: Computador? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.computadoras_update_activity)

        spinnerComputadoras = findViewById(R.id.spinnerComputadoras)
        editTextModelo = findViewById(R.id.editTextModelo)
        editTextPrecio = findViewById(R.id.editTextPrecio)
        spinnerTipo = findViewById(R.id.spinnerTipo)
        editTextAnoLanzamiento = findViewById(R.id.editTextAñoLanzamiento)
        checkBoxEnProduccion = findViewById(R.id.checkBoxEnProduccion)
        botonActualizar = findViewById(R.id.botonActualizar)

        crudService = CRUDService(this)

        configurarSpinnerTipo()
        cargarComputadoras()
        configurarSpinnerComputadoras()
    }

    private fun cargarComputadoras() {
        CoroutineScope(Dispatchers.Main).launch {
            computadoras = crudService.leerComputadores()
            val adapter = ArrayAdapter(this@UpdateComputadorasActivity, android.R.layout.simple_spinner_item, computadoras.map { it.modelo })
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerComputadoras.adapter = adapter
        }
    }

    private fun configurarSpinnerComputadoras() {
        spinnerComputadoras.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                computadoraSeleccionada = computadoras[position]
                cargarDatosComputadora(computadoraSeleccionada!!)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                computadoraSeleccionada = null
            }
        }
    }

    private fun configurarSpinnerTipo() {
        val tipos = TipoComputador.values().map { it.name }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tipos)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTipo.adapter = adapter
    }

    private fun cargarDatosComputadora(computadora: Computador) {
        editTextModelo.setText(computadora.modelo)
        editTextPrecio.setText(computadora.precio.toString())
        spinnerTipo.setSelection(TipoComputador.values().indexOf(computadora.tipo))
        editTextAnoLanzamiento.setText(computadora.añoLanzamiento.toString())
        checkBoxEnProduccion.isChecked = computadora.enProduccion
    }

    fun onActualizarComputadoraClick(view: View) {
        computadoraSeleccionada?.let {
            val modelo = editTextModelo.text.toString()
            val precio = editTextPrecio.text.toString().toDoubleOrNull() ?: 0.0
            val tipo = TipoComputador.valueOf(spinnerTipo.selectedItem.toString())
            val anoLanzamiento = editTextAnoLanzamiento.text.toString().toIntOrNull() ?: 0
            val enProduccion = checkBoxEnProduccion.isChecked

            val computadorActualizado = Computador(modelo, precio, tipo, anoLanzamiento, enProduccion, it.nombreMarca)
            try {
                crudService.actualizarComputador(it.modelo, computadorActualizado)
                Toast.makeText(this, "Computadora actualizada con éxito", Toast.LENGTH_SHORT).show()
                cargarComputadoras()
            } catch (e: Exception) {
                Toast.makeText(this, "Error al actualizar la computadora", Toast.LENGTH_SHORT).show()
            }
        } ?: Toast.makeText(this, "Por favor, seleccione una computadora", Toast.LENGTH_SHORT).show()
    }
}