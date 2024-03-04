package com.example.guzmanreyesproyecto.computadoras.read

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.ComponentActivity
import com.example.guzmanreyesproyecto.R
import com.example.guzmanreyesproyecto.service.CRUDService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReadComputadorasActivity : ComponentActivity() {
    private lateinit var listViewComputadoras: ListView
    private lateinit var crudService: CRUDService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.computadoras_read_activity)

        listViewComputadoras = findViewById(R.id.listViewComputadoras)
        crudService = CRUDService(this)

        cargarComputadoras()
    }

    private fun cargarComputadoras() {
        CoroutineScope(Dispatchers.Main).launch {
            val computadoras = crudService.leerComputadores()
            println("COMPUTADORAS" + computadoras)
            val adapter = ArrayAdapter(this@ReadComputadorasActivity, android.R.layout.simple_list_item_1, computadoras.map { "${it.nombreMarca} - ${it.modelo}" })
            listViewComputadoras.adapter = adapter
        }
    }
}