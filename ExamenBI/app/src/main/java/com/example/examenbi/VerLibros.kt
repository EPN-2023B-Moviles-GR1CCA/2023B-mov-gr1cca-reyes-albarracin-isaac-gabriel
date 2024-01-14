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

class VerLibros : AppCompatActivity() {
    companion object {
        var idLibroSeleccionada = 0
    }

    @SuppressLint("MissingFlattedID","MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_libros)
        val idLibreria = Libreria.idLibreriaSeleccionado
        var libreriaAux = DbLibreria(null, "", "", "", "", this)

        val textViewAutor = findViewById<TextView>(R.id.tv_libreriaVerLibros)
        textViewAutor.text = "Libreria: "+ libreriaAux.getLibreriaById(idLibreria).getnombreLibreria()

        val btnCrearLibro = findViewById<Button>(R.id.btn_crearLibro)
        btnCrearLibro.setOnClickListener {
            irActividad(Libro::class.java)
        }
        showListView(idLibreria)
    }

    fun showListView(id: Int) {
        // ListView Libros
        val objetoLibro = DbLibro(null, "", "", "", "", 0, this)
        val listViewLibros= findViewById<ListView>(R.id.listView_libro)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            objetoLibro.showLibros(id)
        )
        listViewLibros.adapter = adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listViewLibros)
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
        idLibroSeleccionada = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar_librerias -> {
                irActividad(ActualizarLibro::class.java)
                return true
            }
            R.id.mi_eliminar_librerias -> {
                abrirDialogo()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar este libro?")
        builder.setPositiveButton(
            "SI",
            DialogInterface.OnClickListener { dialog, which ->
                val libro = DbLibro(null, "", "", "", "", 0, this)
                val resultado = libro.deleteLibro(idLibroSeleccionada)
                if (resultado > 0) {
                    Toast.makeText(this, "REGISTRO ELIMINADO", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "ERROR AL ELIMINAR REGISTRO", Toast.LENGTH_LONG).show()
                }
                val idLibreria = Libreria.idLibreriaSeleccionado
                showListView(idLibreria)
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