package com.example.examenbi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.examenbi.ui.theme.ExamenBITheme
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.example.examenbi.db.DbHelper
import com.example.examenbi.db.DbLibreria
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var lv_librerias: ListView
    private val libreriaList: MutableList<DbLibreria> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //lv_librerias = findViewById(R.id.lv_librerias)

        fetchLibreriasFromFirestore()

        lv_librerias.setOnItemClickListener { _, _, position, _ ->
            val libreria = libreriaList[position]
            Toast.makeText(this, "Seleccionaste: ${libreria.nombreLibreria}", Toast.LENGTH_SHORT).show()
            // Puedes agregar más acciones aquí, como abrir una nueva actividad
        }

    }

    private fun fetchLibreriasFromFirestore() {
        val db = FirebaseFirestore.getInstance()
        db.collection("Librerias")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val libreria = document.toObject(DbLibreria::class.java)
                    libreriaList.add(libreria)
                }
                // Update the ListView adapter
                val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    libreriaList.map { it.nombreLibreria }
                )
                lv_librerias.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Log.w("MainActivity", "Error getting documents: ", exception)
            }
    }


}
