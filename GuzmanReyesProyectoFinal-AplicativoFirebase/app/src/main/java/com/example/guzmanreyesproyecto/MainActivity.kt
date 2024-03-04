package com.example.guzmanreyesproyecto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.example.guzmanreyesproyecto.computadoras.ComputadorasActivity
import com.example.guzmanreyesproyecto.marca.MarcaActivity
import com.example.guzmanreyesproyecto.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMarcas = findViewById<Button>(R.id.btnMarcas)
        val btnComputadoras = findViewById<Button>(R.id.btnComputadoras)

        btnMarcas.setOnClickListener {
            val intent = Intent(this, MarcaActivity::class.java)
            startActivity(intent)
        }
        btnComputadoras.setOnClickListener {
            val intent = Intent(this, ComputadorasActivity::class.java)
            startActivity(intent)
        }

    }
}