package com.example.guzmanreyesproyecto.marca.read

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guzmanreyesproyecto.R
import com.example.guzmanreyesproyecto.models.MarcaComputador

class MarcasReadAdapter(private val marcas: List<MarcaComputador>) : RecyclerView.Adapter<MarcasReadAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreTextView: TextView = view.findViewById(R.id.nombreTextView)
        val paisOrigenTextView: TextView = view.findViewById(R.id.paisOrigenTextView)
        val anioFundacionTextView: TextView = view.findViewById(R.id.anioFundacionTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.marca_read_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val marca = marcas[position]
        holder.nombreTextView.text = marca.nombre
        holder.paisOrigenTextView.text = marca.paisOrigen
        holder.anioFundacionTextView.text = marca.añoFundacion.toString()
    }

    override fun getItemCount() = marcas.size
}
