package com.example.examenbi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.examenbi.db.DbLibro

class ActualizarLibro:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_libro)

        val idLibro = VerLibros.idLibroSeleccionada
        var libro = DbLibro(null, "", "", "", "", 0, this)
        libro = libro.getLibroById(idLibro)

        var id = findViewById<EditText>(R.id.et_upd_idLibro)
        id.setText(libro.getidLibro().toString())
        var nombre = findViewById<EditText>(R.id.et_upd_nombLibro)
        nombre.setText(libro.getnombreLibro())
        var autor = findViewById<EditText>(R.id.et_upd_autorLibro)
        autor.setText(libro.getautorLibro())
        var precio = findViewById<EditText>(R.id.et_upd_precioLibro)
        precio.setText(libro.getprecio())
        var fecha = findViewById<EditText>(R.id.et_upd_fechaLibro)
        fecha.setText(libro.getfechaPublicacion())
        var fk = findViewById<EditText>(R.id.et_upd_idAlbLibro)
        fk.setText(libro.getidLibreria().toString())

        val btn_actualizar_libro = findViewById<Button>(R.id.btn_upd_Libro)
        btn_actualizar_libro.setOnClickListener {
            libro.setnombreLibro(nombre.text.toString())
            libro.setautorLibro(autor.text.toString())
            libro.setprecio(precio.text.toString())
            libro.setfechaPublicacion(fecha.text.toString())
            libro.setidLibreria(fk.text.toString().toInt())
            val resultado = libro.updateLibro()

            if (resultado > 0) {
                Toast.makeText(this, "REGISTRO ACTUALIZADO", Toast.LENGTH_LONG).show()
                cleanEditText()
            } else {
                Toast.makeText(this, "ERROR AL ACTUALIZAR REGISTRO", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun cleanEditText() {
        var id = findViewById<EditText>(R.id.et_upd_idLibro)
        id.setText("")
        var nombre = findViewById<EditText>(R.id.et_upd_nombLibro)
        nombre.setText("")
        var autor = findViewById<EditText>(R.id.et_upd_autorLibro)
        autor.setText("")
        var precio = findViewById<EditText>(R.id.et_upd_precioLibro)
        precio.setText("")
        var fecha = findViewById<EditText>(R.id.et_upd_fechaLibro)
        fecha.setText("")
        var fk = findViewById<EditText>(R.id.et_upd_idAlbLibro)
        fk.setText("")
        id.requestFocus()
    }
}