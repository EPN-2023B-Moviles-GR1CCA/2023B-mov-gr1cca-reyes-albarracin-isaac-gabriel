package com.example.gr1accigra2023b

class BBaseDatosMemoria {
    //COMPANION OBJECT

    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init{
            arregloBEntrenador
                .add(
                    BEntrenador(1,"Isaac", "a@a.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(1,"Adrian", "b@b.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(1,"Carolina", "c@c.com")
                )
        }
    }
}