import java.util.Date

fun main(args:Array<String>){
    //Agregando Libros

    //Agregando Librerias

    //Menú
    do {
        var opcionUsuario = false
        println(
            "APLICACIONES MÓVILES - CRUD -> LIBRERIA - LIBRO | ISAAC REYES:" +
                    "\n1) Gestionar Libros" +
                    "\n2) Gestionar Librerias" +
                    "\n3) Salir"
        )
        val opTabla = readln().toInt()
        if (opTabla != 3) {
            var opcionAux = false
            var textoConsola = ""
            textoConsola += if (opTabla == 1) {
                "Elija una opción:" +
                        "\n1) Ingresar nuevo libro" +
                        "\n2) Ver libros" +
                        "\n3) Actualizar libro" +
                        "\n4) Eliminar libro" +
                        "\n5) Volver"
            } else {
                "Elija una opción:" +
                        "\n1) Ingresar nueva libreria" +
                        "\n2) Ver librerias" +
                        "\n3) Actualizar librerias" +
                        "\n4) Eliminar librerias" +
                        "\n5) Volver"
            }
            while (!opcionAux) {
                println(textoConsola)
                val opcionCrud = readln().toInt()
                when (opcionCrud) {
                    (1) -> {
                        if (opTabla == 1) {
                            print("Título Libro: ")
                            val nombre = readln()
                            print("Autor Libro: ")
                            val autor = readln()
                            print("Fecha publicación (AAAA-MM-DD): ")
                            val fecha = readln()
                            val fechaSplit = fecha.split("-")
                            val fechaAux: Date =
                                Date(fechaSplit[0].toInt() - 1900, fechaSplit[1].toInt() - 1, fechaSplit[2].toInt())
                            print("Precio: ")
                            val precio = readln().toDouble()

                            val newLibro =
                                Libro(Libro.getNumLibros() + 1, nombre, autor, fechaAux, precio)
                            newLibro.insertLibro()

                        } else {
                            print("Nombre Libreria: ")
                            val nombre = readln()
                            print("Ubicación Libreria: ")
                            val ubicacion = readln()
                            print("Fecha (AAAA-MM-DD): ")
                            val fecha = readln()
                            val fechaSplit = fecha.split("-")
                            val fechaAux: Date =
                                Date(fechaSplit[0].toInt() - 1900, fechaSplit[1].toInt() - 1, fechaSplit[2].toInt())
                            print("Público 1)SI 2)NO: ")
                            val estadoAux = readln().toInt()
                            var estado: Boolean

                            if (estadoAux == 1)  {
                                estado = true
                            } else {
                                estado = false
                            }

                            println("\n***LISTA DE LIBROS DISPONIBLES***")
                            Libro.selectLibro()

                            print("Seleccione los libros a agregar a la libreria (1,2,...): ")
                            val stringLibros = readLine().toString()
                            val splitLibros = stringLibros.split(",")
                            val intLibrosArray = splitLibros.map { it.toInt() }.toTypedArray()
                            val listaLibreria: ArrayList<Libro> = Libreria.setArrayListLibroLibreria(intLibrosArray)

                            val newLibreria = Libreria(Libreria.getNumLibreria() + 1, nombre, ubicacion, fechaAux, estado, listaLibreria)
                            newLibreria.insertLibreria(intLibrosArray.size)
                        }
                    }

                    (2) -> {
                        if (opTabla == 1) {
                            println("LISTA DE LIBROS:")
                            Libro.selectLibro()
                        } else {
                            println("LISTA DE LIBRERIAS")
                            Libreria.selectLibreria()
                        }
                    }

                    (3) -> {
                        if (opTabla == 1) {
                            print("Ingrese el nombre del libro ha actualizar: ")
                            val searchLibro = readln()
                            Libro.updateLibro(searchLibro)
                        } else {
                            print("Ingrese el nombre de la libreria ha actualizar: ")
                            val searchLibreria = readln()
                            Libreria.updateLibreria(searchLibreria)
                        }
                    }

                    (4) -> {
                        if (opTabla == 1) {
                            print("Ingrese el nombre del libro ha eliminar: ")
                            val searchLibro = readln()
                            Libro.deleteLibro(searchLibro)
                        } else {
                            print("Ingrese el nombre de la libreria ha eliminar: ")
                            val searchLibreria = readln()
                            Libreria.deleteLibreria(searchLibreria)
                        }
                    }

                    (5) -> {
                        opcionAux = true
                    }
                }
            }
        } else {
            opcionUsuario = true
        }
    } while (!opcionUsuario)
}