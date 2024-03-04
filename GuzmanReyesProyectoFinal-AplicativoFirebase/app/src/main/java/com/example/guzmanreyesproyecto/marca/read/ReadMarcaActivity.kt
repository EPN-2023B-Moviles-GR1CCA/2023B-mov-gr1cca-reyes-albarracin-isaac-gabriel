package com.example.guzmanreyesproyecto.marca.read

import android.os.Build
import com.example.guzmanreyesproyecto.service.CRUDService
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guzmanreyesproyecto.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReadMarcaActivity : ComponentActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var marcasReadAdapter: MarcasReadAdapter
    private lateinit var crudService: CRUDService

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.marca_read_activity)


        recyclerView = findViewById(R.id.marcasRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        crudService = CRUDService(this)

        CoroutineScope(Dispatchers.Main).launch {
            val marcas = crudService.leerMarcas()

            println("************MARCAS: " + marcas)

            marcasReadAdapter = MarcasReadAdapter(marcas)
            recyclerView.adapter = marcasReadAdapter
        }
    }
}