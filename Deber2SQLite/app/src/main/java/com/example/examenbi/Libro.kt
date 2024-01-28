package com.example.examenbi

import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import com.example.examenbi.db.DbLibro
import android.annotation.SuppressLint

class Libro:AppCompatActivity() {

    var idItemSeleccionado = 0

    @SuppressLint("MissingFlattedID","MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_libro)

        val nombre = findViewById<EditText>(R.id.editText_libro)
        nombre.requestFocus()
        val autor = findViewById<EditText>(R.id.editText_autor)
        val precio = findViewById<EditText>(R.id.editText_precio)
        val fecha = findViewById<EditText>(R.id.editText_fecha)
        val idLibreria = findViewById<EditText>(R.id.editText_idlibreria)

        val btnInsertar = findViewById<Button>(R.id.btn_insert)
        btnInsertar.setOnClickListener {
            val libro: DbLibro = DbLibro(
                null,
                nombre.text.toString(),
                autor.text.toString(),
                precio.text.toString(),
                fecha.text.toString(),
                idLibreria.text.toString().toInt(),
                this
            )
            val resultado = libro.insertLibro()

            if (resultado > 0) {
                Toast.makeText(this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show()
                cleanEditText()
            } else {
                Toast.makeText(this, "ERROR AL INSERTAR REGISTRO", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun cleanEditText() {
        val nombre = findViewById<EditText>(R.id.editText_libro)
        nombre.setText("")
        val autor = findViewById<EditText>(R.id.editText_autor)
        autor.setText("")
        val precio = findViewById<EditText>(R.id.editText_precio)
        precio.setText("")
        val fecha = findViewById<EditText>(R.id.editText_fecha)
        fecha.setText("")
        val idLibreria = findViewById<EditText>(R.id.editText_idlibreria)
        idLibreria.setText("")
        nombre.requestFocus()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Llenamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menulibro, menu)
        // Obtener el id del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar_librerias -> {
                "${idItemSeleccionado}"
                return true
            }
            R.id.mi_eliminar_librerias -> {
                //abrirDialogo()
                "${idItemSeleccionado}"
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

}