package com.example.examenbi.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class DbLibro (
    //Atributos
    private var idLibro: Int?,
    private var nombreLibro: String,
    private var autorLibro: String,
    private var precio: String,
    private var fechaPublicacion: String,
    private var fkLibreria: Int,
    private val context: Context?
) {
    init {
        nombreLibro
        autorLibro
        precio
        fechaPublicacion
        fkLibreria
        context
    }

    fun setidLibro(idLibro: Int) {
        this.idLibro = idLibro
    }

    fun setnombreLibro(nombreLibro: String) {
        this.nombreLibro = nombreLibro
    }

    fun setautorLibro(autorLibro: String) {
        this.autorLibro = autorLibro
    }

    fun setprecio(precio: String) {
        this.precio = precio
    }

    fun setfechaPublicacion(fechaPublicacion: String) {
        this.fechaPublicacion = fechaPublicacion
    }

    fun setidLibreria(idLibreria: Int) {
        this.fkLibreria = idLibreria
    }

    fun getidLibro(): Int? {
        return idLibro
    }

    fun getidLibreria(): Int {
        return fkLibreria
    }

    fun getnombreLibro(): String {
        return nombreLibro
    }

    fun getautorLibro(): String {
        return autorLibro
    }

    fun getprecio(): String {
        return precio
    }

    fun getfechaPublicacion(): String {
        return fechaPublicacion
    }

    fun insertLibro(): Long {
        val dbHelper: DbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        val values: ContentValues = ContentValues()
        values.put("nombre_libro", this.nombreLibro)
        values.put("autor_libro", this.autorLibro)
        values.put("precio", this.precio)
        values.put("fechaPublicacion", this.fechaPublicacion)
        values.put("IDlibreria", this.fkLibreria)

        return db.insert("t_libro", null, values)
    }

    fun showLibros(id: Int): ArrayList<DbLibro> {
        val dbHelper: DbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        var listaLibros = ArrayList<DbLibro>()
        var libro: DbLibro
        var cursorLibro: Cursor? = null

        // Ver si el id: Int es diferente de null
        cursorLibro = db.rawQuery("SELECT * FROM t_libro WHERE IDlibreria=${id+1}", null)

        if (cursorLibro.moveToFirst()) {
            do {
                libro = DbLibro(null, "", "", "", "", 0, null)

                libro.setidLibro(cursorLibro.getString(0).toInt())
                libro.setnombreLibro(cursorLibro.getString(1))
                libro.setautorLibro(cursorLibro.getString(2))
                libro.setprecio(cursorLibro.getString(3))
                libro.setfechaPublicacion(cursorLibro.getString(4))
                libro.setidLibreria(cursorLibro.getString(5).toInt())
                listaLibros.add(libro)
            } while (cursorLibro.moveToNext())
        }

        cursorLibro.close()
        return listaLibros
    }

    fun getLibroById(id: Int): DbLibro{
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        var libro = DbLibro(null, "", "", "", "",0, this.context)
        var cursor: Cursor? = null

        cursor = db.rawQuery("SELECT * FROM t_libro WHERE id_libro = ${id+1}", null)

        if (cursor.moveToFirst()) {
            do {
                libro.setidLibro(cursor.getString(0).toInt())
                libro.setnombreLibro(cursor.getString(1))
                libro.setautorLibro(cursor.getString(2))
                libro.setprecio(cursor.getString(3))
                libro.setfechaPublicacion(cursor.getString(4))
                libro.setidLibreria(cursor.getString(5).toInt())
            } while (cursor.moveToNext())
        }

        cursor.close()
        return libro
    }

    fun updateLibro(): Int {
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        val values: ContentValues = ContentValues()
        values.put("nombre_libro", this.nombreLibro)
        values.put("autor_libro", this.autorLibro)
        values.put("precio", this.precio)
        values.put("fechaPublicacion", this.fechaPublicacion)
        values.put("IDlibreria", this.fkLibreria)

        return db.update("t_libro", values, "id_libro="+this.idLibro, null)
    }

    fun deleteLibro(id: Int): Int{
        val dbHelper = DbHelper(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        return db.delete("t_libro", "id_libro="+(id+1), null)
    }

    override fun toString(): String {
        val salida =
            "Núm. tema: ${idLibro}\n" +
                    "Libro: ${nombreLibro}\n" +
                    "Autor: ${autorLibro}\n" +
                    "Precio: ${precio} \n" +
                    "Fecha de publicación: ${fechaPublicacion}\n" +
                    "Núm. Libreria: ${fkLibreria}"

        return salida
    }
}