package com.example.guzmanreyesproyecto.models

import java.time.LocalDate

data class MarcaComputador(
    val nombre: String,
    val paisOrigen: String,
    val añoFundacion: Int,
    val esIndependiente: Boolean,
    val fechaRegistro: LocalDate,
    val computadores: MutableList<Computador> = mutableListOf()
) {
    fun agregarComputador(computador: Computador) {
        computadores.add(computador)
    }
}