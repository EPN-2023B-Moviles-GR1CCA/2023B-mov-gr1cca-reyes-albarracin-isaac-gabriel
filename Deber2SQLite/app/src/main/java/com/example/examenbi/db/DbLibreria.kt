package com.example.examenbi.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class DbLibreria (
    // Atributos
    private var idLibreria: Int?,
    private var nombreLibreria: String,
    private var ubicacionLibreria: String,
    private var fechaCreacion: String,
    private var esPublica: String,
    private val context: Context?
) {
    init {
        idLibreria
        nombreLibreria
        ubicacionLibreria
        fechaCreacion
        esPublica
        context
    }

    fun setidLibreria(idLibreria: Int) {
        this.idLibreria = idLibreria
    }

    fun setnombreLibreria(nombreLibreria: String) {
        this.nombreLibreria = nombreLibreria
    }

    fun setubicacionLibreria(ubicacionLibreria: String) {
        this.ubicacionLibreria = ubicacionLibreria
    }

    fun setfechaCreacion(fechaCreacion: String) {
        this.fechaCreacion = fechaCreacion
    }

    fun setesPublica(esPublica: String) {
        this.esPublica = esPublica
    }

    fun getidLibreria(): Int? {
        return idLibreria
    }

    fun getnombreLibreria(): String {
        return nombreLibreria
    }

    fun getubicacionLibreria(): String {
        return ubicacionLibreria
    }

    fun getfechaCreacion(): String {
        return fechaCreacion
    }

    fun getesPublica(): String {
        return esPublica
    }

    fun insertLibreria(): Long {
        val dbHelper: DbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        val values: ContentValues = ContentValues()
        values.put("nombre_libreria", this.nombreLibreria)
        values.put("ubicacion_libreria", this.ubicacionLibreria)
        values.put("fechaCreacion", this.fechaCreacion)
        values.put("esPublica", this.esPublica)

        return db.insert("t_libreria", null, values)
    }

    fun showLibrerias(): ArrayList<DbLibreria> {
        val dbHelper: DbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        var lista = ArrayList<DbLibreria>()
        var libreria: DbLibreria
        var cursor: Cursor? = null

        cursor = db.rawQuery("SELECT * FROM t_libreria", null)

        if (cursor.moveToFirst()) {
            do {
                libreria = DbLibreria(null, "", "", "", "", null)

                libreria.setidLibreria(cursor.getString(0).toInt())
                libreria.setnombreLibreria(cursor.getString(1))
                libreria.setubicacionLibreria(cursor.getString(2))
                libreria.setfechaCreacion(cursor.getString(3))
                libreria.setesPublica(cursor.getString(4))
                lista.add(libreria)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return lista
    }

    fun getLibreriaById(id: Int): DbLibreria{
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        var libreria = DbLibreria(null, "", "", "", "", this.context)
        var cursor: Cursor? = null

        cursor = db.rawQuery("SELECT * FROM t_libreria WHERE id_libreria = ${id+1}", null)

        if (cursor.moveToFirst()) {
            do {
                libreria.setidLibreria(cursor.getString(0).toInt())
                libreria.setnombreLibreria(cursor.getString(1))
                libreria.setubicacionLibreria(cursor.getString(2))
                libreria.setfechaCreacion(cursor.getString(3))
                libreria.setesPublica(cursor.getString(4))
            } while (cursor.moveToNext())
        }

        cursor.close()
        return libreria
    }

    fun updateLibreria(): Int {
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        val values: ContentValues = ContentValues()
        values.put("nombre_libreria", this.nombreLibreria)
        values.put("ubicacion_libreria", this.ubicacionLibreria)
        values.put("fechaCreacion", this.fechaCreacion)
        values.put("esPublica", this.esPublica)

        return db.update("t_libreria", values, "id_libreria="+this.idLibreria, null)
    }

    fun deleteLibreria(id: Int): Int{
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        return db.delete("t_libreria", "id_libreria="+(id+1), null)
    }

    override fun toString(): String {
        val salida =
            "Núm. Libreria: ${idLibreria}\n" +
                    "Libreria: ${nombreLibreria}\n" +
                    "Ubicacion: ${ubicacionLibreria}\n" +
                    "Fecha de creacion: ${fechaCreacion}\n" +
                    "Es Publica: ${esPublica}"

        return salida
    }
}