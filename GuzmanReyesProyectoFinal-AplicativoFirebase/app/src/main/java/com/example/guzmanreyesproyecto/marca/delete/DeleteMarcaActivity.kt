package com.example.guzmanreyesproyecto.marca.delete

import com.example.guzmanreyesproyecto.service.CRUDService
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guzmanreyesproyecto.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeleteMarcaActivity : ComponentActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adaptador: MarcasEliminarAdapter
    private lateinit var crudService: CRUDService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.marca_delete_activity)

        recyclerView = findViewById(R.id.marcasEliminarView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        crudService = CRUDService(this)

        actualizarLista()
    }

    private fun eliminarMarca(nombre: String) {
        crudService.eliminarMarca(nombre)
        actualizarLista()
    }

    private fun actualizarLista() {
        CoroutineScope(Dispatchers.Main).launch {
            val marcas = crudService.leerMarcas()
            adaptador = MarcasEliminarAdapter(marcas, this@DeleteMarcaActivity::eliminarMarca)
            recyclerView.adapter = adaptador
        }
    }
}