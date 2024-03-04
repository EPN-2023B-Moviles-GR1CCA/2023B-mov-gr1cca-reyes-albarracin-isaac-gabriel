package com.example.guzmanreyesproyecto.marca.update

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.ComponentActivity
import com.example.guzmanreyesproyecto.R
import com.example.guzmanreyesproyecto.service.CRUDService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateMarcaActivity : ComponentActivity() {

    private lateinit var listView: ListView
    private val crudService = CRUDService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.marca_update_activity)

        listView = findViewById(R.id.listViewMarcas)
        mostrarMarcas()
    }

    private fun mostrarMarcas() {
        CoroutineScope(Dispatchers.Main).launch {
            val marcas = crudService.leerMarcas() // Asume que leerMarcas es una suspending function
            val adapter = ArrayAdapter(this@UpdateMarcaActivity, android.R.layout.simple_list_item_1, marcas.map { it.nombre })
            listView.adapter = adapter

            listView.setOnItemClickListener { _, _, position, _ ->
                val intent = Intent(this@UpdateMarcaActivity, UpdateSingleMarcaActivity::class.java).apply {
                    putExtra("nombreMarca", marcas[position].nombre)
                }
                startActivity(intent)
            }
        }
    }

}
