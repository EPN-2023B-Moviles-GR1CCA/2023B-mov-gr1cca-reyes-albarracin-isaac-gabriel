package com.example.examenbi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.examenbi.db.DbLibreria

class ActualizarLibreria: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_libreria)

        val idLibreria = Libreria.idLibreriaSeleccionado
        var libreria = DbLibreria(null, "", "", "", "", this)
        libreria = libreria.getLibreriaById(idLibreria)

        var id = findViewById<EditText>(R.id.et_upd_idLibria)
        id.setText(libreria.getidLibreria().toString())
        var nombre = findViewById<EditText>(R.id.et_upd_nombLibria)
        nombre.setText(libreria.getnombreLibreria())
        var ubi = findViewById<EditText>(R.id.et_upd_ubiLibria)
        ubi.setText(libreria.getubicacionLibreria())
        var fecha = findViewById<EditText>(R.id.et_upd_fechaLibria)
        fecha.setText(libreria.getfechaCreacion())
        var es = findViewById<EditText>(R.id.et_upd_esLibria)
        es.setText(libreria.getesPublica())

        val btn_actualizarLibreria = findViewById<Button>(R.id.btn_upd_Libreria)
        btn_actualizarLibreria.setOnClickListener {
            // Libreria actualizada
            libreria.setnombreLibreria(nombre.text.toString())
            libreria.setubicacionLibreria(ubi.text.toString())
            libreria.setfechaCreacion(fecha.text.toString())
            libreria.setesPublica(es.text.toString())
            val resultado = libreria.updateLibreria()

            if (resultado > 0) {
                Toast.makeText(this, "REGISTRO ACTUALIZADO", Toast.LENGTH_LONG).show()
                cleanEditText()
            } else {
                Toast.makeText(this, "ERROR AL ACTUALIZAR REGISTRO", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun cleanEditText() {
        val id = findViewById<EditText>(R.id.et_upd_idLibria)
        id.setText("")
        val nombre = findViewById<EditText>(R.id.et_upd_nombLibria)
        nombre.setText("")
        val ubi = findViewById<EditText>(R.id.et_upd_ubiLibria)
        ubi.setText("")
        val fecha = findViewById<EditText>(R.id.et_upd_fechaLibria)
        fecha.setText("")
        val es = findViewById<EditText>(R.id.et_upd_esLibria)
        es.setText("")
        id.requestFocus()
    }


}