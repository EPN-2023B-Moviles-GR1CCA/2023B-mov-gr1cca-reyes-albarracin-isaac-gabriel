package com.example.examenbi

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.examenbi.db.DbLibreria
import com.example.examenbi.db.DbLibro
import android.annotation.SuppressLint
import android.util.Log
import com.example.examenbi.Libreria.Companion.idLibreriaSeleccionado
import com.google.firebase.firestore.FirebaseFirestore

class VerLibros : AppCompatActivity() {
    private var librosList = mutableListOf<DbLibro>()
    private lateinit var listView_libro: ListView
    companion object {
        private const val TAG = "verLibros"
    }

    @SuppressLint("MissingFlattedID","MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_libros)

        listView_libro = findViewById(R.id.listView_libro)
        fetchLibrosFromFirestore()

        listView_libro.setOnItemClickListener { _, _, position, _ ->
            val libro = librosList[position]
            //... (show context menu with options to edit or delete)
        }
    }

    private fun fetchLibrosFromFirestore() {
        val db = FirebaseFirestore.getInstance()
        db.collection("Libros")
            .whereEqualTo("fkLibreria", idLibreriaSeleccionado)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val tema = document.toObject(DbLibro::class.java)
                    librosList.add(tema)
                }
                // Set up the ListView adapter
                setupListViewAdapter()
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
    }

    private fun setupListViewAdapter() {
        val temasStringsList = librosList.map { it.nombreLibro }
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            temasStringsList
        )
        listView_libro.adapter = adapter
    }

    // ... (other methods and class logic)

}