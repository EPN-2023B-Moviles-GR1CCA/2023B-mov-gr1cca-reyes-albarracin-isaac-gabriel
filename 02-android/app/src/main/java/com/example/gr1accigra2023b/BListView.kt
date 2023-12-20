package com.example.gr1accigra2023b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class BListView : AppCompatActivity() {
    val arreglo = BBaseDatosMemoria.arregloBEntrenador
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)

        val listView=findViewById<ListView>(R.id.lv_list_view)
        val adaptador=ArrayAdapter(
            this,//Contexto
            android.R.layout.simple_list_item_1, //como se va a ver XML
            arreglo
        )
        listView.adapter=adaptador
        adaptador.notifyDataSetChanged()


        val botonAnadirListView = findViewById<Button>(
            R.id.btn_anadir_list_view)
        botonAnadirListView
            .setOnClickListener{
                anadirEntrenador(adaptador)
            }

        registerForContextMenu(listView)
    }//finalizar OnCreate

    var posicionItemSeleccionado=0
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //llenamos las opciones del menu
        val inflater=menuInflater
        //inflater.inflate(R.menu.menu, menu)
        //obtener el id del ArrayListSeleccionado
        val info=menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion=info.position
        posicionItemSeleccionado=posicion
    }



    fun anadirEntrenador(
        adaptador: ArrayAdapter<BEntrenador>
    ){
        arreglo.add(
            BEntrenador(
                1,
                "Isaac",
                "Descripcion"
            )
        )
        adaptador.notifyDataSetChanged()
    }

}