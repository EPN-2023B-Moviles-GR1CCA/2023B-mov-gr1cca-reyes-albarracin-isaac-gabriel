package com.example.guzmanreyesproyecto.computadoras

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.example.guzmanreyesproyecto.computadoras.create.AddComputadoraActivity
import com.example.guzmanreyesproyecto.computadoras.delete.DeleteComputadoraActivity
import com.example.guzmanreyesproyecto.computadoras.read.ReadComputadorasActivity
import com.example.guzmanreyesproyecto.computadoras.update.UpdateComputadorasActivity
import com.example.guzmanreyesproyecto.R

class ComputadorasActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.computadoras_activity)

        val btnAddComputadora = findViewById<Button>(R.id.btnAddComputadora)
        val btnReadComputadora = findViewById<Button>(R.id.btnReadComputadora)
        val btnDeleteComputadora = findViewById<Button>(R.id.btnDeleteComputadora)
        val btnUpdateComputadora = findViewById<Button>(R.id.btnUpdateComputadora)

        btnAddComputadora.setOnClickListener {
            val intent = Intent(this, AddComputadoraActivity::class.java)
            startActivity(intent)
        }
        btnReadComputadora.setOnClickListener {
            val intent = Intent(this, ReadComputadorasActivity::class.java)
            startActivity(intent)
        }
        btnDeleteComputadora.setOnClickListener {
            val intent = Intent(this, DeleteComputadoraActivity::class.java)
            startActivity(intent)
        }
        btnUpdateComputadora.setOnClickListener {
            val intent = Intent(this, UpdateComputadorasActivity::class.java)
            startActivity(intent)
        }
    }
}