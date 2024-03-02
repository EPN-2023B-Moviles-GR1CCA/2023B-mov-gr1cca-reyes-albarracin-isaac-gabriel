package com.example.examenbi.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context?) : SQLiteOpenHelper(
    context,
    "DBExamen01.db",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaLibreria =
            "CREATE TABLE t_libreria(" +
                    "id_libreria INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre_libreria TEXT NOT NULL," +
                    "ubicacion_asignatura TEXT NOT NULL," +
                    "fechaCreacion TEXT NOT NULL," +
                    "esPublica TEXT NOT NULL);"

        val scriptSQLCrearTablaLibro =
            "CREATE TABLE t_libro(" +
                    "id_libro INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre_libro TEXT NOT NULL," +
                    "autor_libro TEXT NOT NULL," +
                    "precio TEXT NOT NULL," +
                    "fechaPublicacion TEXT NOT NULL, " +
                    "IDlibreria INTEGER NOT NULL," +
                    "FOREIGN KEY(IDlibreria) REFERENCES t_libreria(id_libreria));"

        db?.execSQL(scriptSQLCrearTablaLibreria)
        db?.execSQL(scriptSQLCrearTablaLibro)
    }

    // Se ejecuta cuando la version cambia
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS t_libro")
        db?.execSQL("DROP TABLE IF EXISTS t_libreria")
        onCreate(db)
    }
}