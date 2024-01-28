package com.example.examenbi

import android.content.DialogInterface
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.examenbi.db.DbLibreria
import android.annotation.SuppressLint

class Libreria : AppCompatActivity() {

    companion object{
        var idLibreriaSeleccionado = 0
    }

    @SuppressLint("MissingFlattedID","MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_libreria)
        showListViewLibreria()

        val nombre = findViewById<EditText>(R.id.editText_nombreLibria)
        nombre.requestFocus()
        val ubi = findViewById<EditText>(R.id.editText_ubiLibria)
        val fecha = findViewById<EditText>(R.id.editText_fechaLibria)
        val es = findViewById<EditText>(R.id.editText_esLibria)

        val btnInsertar = findViewById<Button>(R.id.btn_insertarLibria)
        btnInsertar.setOnClickListener {
            val libreria = DbLibreria(
                null,
                nombre.text.toString(),
                ubi.text.toString(),
                fecha.text.toString(),
                es.text.toString(),
                this
            )
            val resultado = libreria.insertLibreria()

            if (resultado > 0) {
                Toast.makeText(this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show()
                cleanEditText()
                showListViewLibreria()
            } else {
                Toast.makeText(this, "ERROR EN INSERTAR REGISTRO", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun cleanEditText() {
        val nombre = findViewById<EditText>(R.id.editText_nombreLibria)
        nombre.setText("")
        val ubi = findViewById<EditText>(R.id.editText_ubiLibria)
        ubi.setText("")
        val fecha = findViewById<EditText>(R.id.editText_fechaLibria)
        fecha.setText("")
        val es = findViewById<EditText>(R.id.editText_esLibria)
        es.setText("")
        nombre.requestFocus()
    }

    fun showListViewLibreria() {
        // ListView Libros
        val libreria = DbLibreria(null, "", "", "", "", this)
        val listView = findViewById<ListView>(R.id.listview_libreria)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            libreria.showLibrerias()
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listView)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Llenamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menulibreria, menu)
        // Obtener el id del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idLibreriaSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar_librerias -> {
                irActividad(ActualizarLibreria::class.java)
                return true
            }
            R.id.mi_eliminar_librerias -> {
                abrirDialogo()
                return true
            }
            R.id.mi_verlibros -> {
                irActividad(VerLibros::class.java)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar esta Libreria?")
        builder.setPositiveButton(
            "SI",
            DialogInterface.OnClickListener { dialog, which ->
                val libreria = DbLibreria(null, "", "", "", "", this)
                val resultado = libreria.deleteLibreria(idLibreriaSeleccionado)
                if (resultado > 0) {
                    Toast.makeText(this, "REGISTRO ELIMINADO", Toast.LENGTH_LONG).show()
                    showListViewLibreria()
                } else {
                    Toast.makeText(this, "ERROR AL ELIMINAR REGISTRO", Toast.LENGTH_LONG).show()
                }
            }
        )
        builder.setNegativeButton(
            "NO",
            null
        )

        val dialogo = builder.create()
        dialogo.show()
    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

}