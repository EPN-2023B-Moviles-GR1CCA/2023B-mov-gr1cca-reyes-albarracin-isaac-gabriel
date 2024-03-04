package com.example.guzmanreyesproyecto.marca.delete

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guzmanreyesproyecto.R
import com.example.guzmanreyesproyecto.models.MarcaComputador

class MarcasEliminarAdapter(private val marcas: List<MarcaComputador>, private val onEliminarClicked: (String) -> Unit) : RecyclerView.Adapter<MarcasEliminarAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreMarcaTextView: TextView = view.findViewById(R.id.nombreMarcaTextView)
        val eliminarButton: Button = view.findViewById(R.id.eliminarButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.marca_delete_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val marca = marcas[position]
        holder.nombreMarcaTextView.text = marca.nombre
        holder.eliminarButton.setOnClickListener { onEliminarClicked(marca.nombre) }
    }

    override fun getItemCount() = marcas.size
}
