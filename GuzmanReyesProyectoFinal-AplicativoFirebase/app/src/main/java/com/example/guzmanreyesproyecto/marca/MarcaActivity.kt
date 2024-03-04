package com.example.guzmanreyesproyecto.marca

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.example.guzmanreyesproyecto.R
import com.example.guzmanreyesproyecto.marca.create.AddMarcaActivity
import com.example.guzmanreyesproyecto.marca.delete.DeleteMarcaActivity
import com.example.guzmanreyesproyecto.marca.read.ReadMarcaActivity
import com.example.guzmanreyesproyecto.marca.update.UpdateMarcaActivity

class MarcaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.marca_activity)

        val btnAddMarca = findViewById<Button>(R.id.btnAddMarca)
        val btnReadMarca = findViewById<Button>(R.id.btnReadMarca)
        val btnDeleteMarca = findViewById<Button>(R.id.btnDeleteMarca)
        val btnUpdateMarca = findViewById<Button>(R.id.btnUpdateMarca)

        btnAddMarca.setOnClickListener {
            val intent = Intent(this, AddMarcaActivity::class.java)
            startActivity(intent)
        }
        btnReadMarca.setOnClickListener {
            val intent = Intent(this, ReadMarcaActivity::class.java)
            startActivity(intent)
        }
        btnDeleteMarca.setOnClickListener {
            val intent = Intent(this, DeleteMarcaActivity::class.java)
            startActivity(intent)
        }
        btnUpdateMarca.setOnClickListener {
            val intent = Intent(this, UpdateMarcaActivity::class.java)
            startActivity(intent)
        }

    }
}