import java.awt.event.ItemEvent
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Libreria(
    //Atributos
    private var idLibreria: Int,
    private var nombreLibreria: String,
    private var ubicacionLibreria: String,
    private var fechaCreacion: Date,
    private var esPublica: Boolean,
    //val libros: MutableList<Libro> = mutableListOf()  // Lista para manejar la relación uno a muchos
    private var listaLibros: ArrayList<Libro>?
)
{
    init{
        idLibreria
        nombreLibreria
        ubicacionLibreria
        fechaCreacion
        esPublica
        listaLibros
    }

    fun setIdlibreria(idLibreria: Int) {
        this.idLibreria = idLibreria
    }

    fun setNombreLibreria(nombreLibreria: String) {
        this.nombreLibreria= nombreLibreria
    }

    fun setUbicacionLibreria(ubicacionLibreria: String) {
        this.ubicacionLibreria = ubicacionLibreria
    }

    fun setFechaCreacion(fechaCreacion: Date) {
        this.fechaCreacion = fechaCreacion
    }

    fun setEsPublica(esPublica: Boolean) {
        this.esPublica = esPublica
    }

    fun getIdLibreria(): Int {
        return idLibreria
    }

    fun getNombreLibreria(): String {
        return nombreLibreria
    }

    fun getUbicacionLibreria(): String {
        return ubicacionLibreria
    }

    fun getFechaCreacion(): Date {
        return fechaCreacion
    }

    fun getEsPublica(): Boolean {
        return esPublica
    }

    companion object {

        fun selectLibreria() {
            //Leer archivo
            var path = Paths.get("src/main/resources/text/libreria.txt")
            Files.lines(path, Charsets.UTF_8).forEach {
                var valorCadena = it.split(",")
                print(
                    "Núm. Libreria: " + valorCadena[0] + "\n"
                            + "Nombre: " + valorCadena[1] + "\n"
                            + "Ubicación: " + valorCadena[2] + "\n"
                            + "Fecha: " + valorCadena[3] + "\n"
                            + "Pública: " + valorCadena[4] + "\n"
                )
                println("Lista de Libros:")
                var path = Paths.get("src/main/resources/text/libreria.txt")
                Files.lines(path, Charsets.UTF_8).forEach {
                    var splitLibros = it.split(",")
                    var idLibro = splitLibros[0]
                    for (i in 5..valorCadena.size - 1) {
                        if (idLibro == valorCadena[i]) {
                            println("\t" + splitLibros[0] + ") " + splitLibros[1] + " - " + splitLibros[2])
                        }
                    }
                }
            }
            println()
        }


        fun updateLibreria(nombreLibreria: String) {
            //Leer archivo
            var path = Paths.get("src/main/resources/text/libreria.txt")
            var flag = false
            var archivoUpdate = ""
            Files.lines(path, Charsets.UTF_8).forEach {
                var valorCadena = it.split(",")
                if (valorCadena[1] == nombreLibreria) {
                    var opcionUpdate = true
                    print(
                        "Núm. Libreria: " + valorCadena[0] + "\n"
                                + "Nombre: " + valorCadena[1] + "\n"
                                + "Ubicación: " + valorCadena[2] + "\n"
                                + "Fecha: " + valorCadena[3] + "\n"
                                + "Pública: " + valorCadena[4] + "\n"
                    )
                    println("Lista de Libros:")
                    var path = Paths.get("src/main/resources/text/libreria.txt")
                    Files.lines(path, Charsets.UTF_8).forEach {
                        var splitLibros = it.split(",")
                        var idLibro = splitLibros[0]
                        for (i in 5..valorCadena.size - 1) {
                            if (idLibro == valorCadena[i]) {
                                println("\t" + splitLibros[0] + ") " + splitLibros[1] + " - " + splitLibros[2])
                            }
                        }
                    }
                    //Ver que atributo desea modificar
                    var newString: String = ""
                    var arrayCadena = arrayOf<String>("0", "0", "0", "0", "0")
                    do {
                        println("Elija el atributo a modificar: 1) Nombre, 2) Ubicación, 3) Fecha, 4) Pública, 5) Lista Libros")
                        var atributoUpdate = readln().toInt()
                        when (atributoUpdate) {
                            (1) -> {
                                print("Ingrese el nuevo nombre: ")
                                var nombre = readln()
                                arrayCadena.set(0, nombre)
                            }

                            (2) -> {
                                print("Ingrese nueva ubicación: ")
                                var ubicacion = readln()
                                arrayCadena.set(1, ubicacion)
                            }

                            (3) -> {
                                print("Ingrese la nueva fecha (AAAA-MM-DD): ")
                                var fecha = readln()
                                var auxFecha = fecha.split("-")
                                val formato = SimpleDateFormat("yyyy-MM-dd")
                                var newFecha: Date =
                                    Date(
                                        auxFecha[0].toInt() - 1900,
                                        auxFecha[1].toInt() - 1,
                                        auxFecha[2].toInt()
                                    )
                                arrayCadena.set(2, formato.format(newFecha))
                            }

                            (4) -> {
                                print("Ingrese el nuevo tipo: ")
                                var publica = readln()
                                arrayCadena.set(3, publica)
                            }

                            (5) -> {
                                print("Seleccione una acción 1) Agregar Libro a la Libreria 2) Eliminar Libro de la Libreria: ")
                                var opcionLista = readln().toInt()
                                if (opcionLista == 1) {
                                    println("***LISTA DE LIBROS***")
                                    Libro.selectLibro()
                                    print("Seleccione los Libros a agregar a la libreria (1,2,...): ")
                                    var newListLibros = readln()
                                    arrayCadena.set(
                                        4,
                                        updateListaLibros(newListLibros, valorCadena[0].toInt())
                                    )
                                } else {
                                    print("Seleccione los libros a eliminar de la libreria (1,2,...): ")
                                    var deleteList = readln()
                                    var auxLista =
                                        deleteListaLibros(deleteList, valorCadena[0].toInt())
                                    arrayCadena.set(4, auxLista)
                                }
                            }
                        }
                        //Ver si desea seguir actualizando la libreria elegida
                        println("¿Desea seguir actualizando la Libreria elegida 1) SI - 2) NO?")
                        var auxOpcion = readln().toInt()
                        if (auxOpcion == 2) {
                            opcionUpdate = false //Terminar update de libreria
                            for (i in 0..arrayCadena.size - 1) {
                                if (arrayCadena[i] == "0") {
                                    if (i == 4) { // Tomando lista de libros original de la libreria
                                        for (j in 5..valorCadena.size - 1) {
                                            if (j == valorCadena.size - 1) {
                                                arrayCadena[i] += valorCadena[j]
                                            } else {
                                                arrayCadena[i] += valorCadena[j] + ","
                                            }
                                        }
                                    } else {
                                        arrayCadena[i] = valorCadena[i + 1]
                                    }
                                }
                            }
                            archivoUpdate += valorCadena[0] + "," + arrayCadena[0] + "," + arrayCadena[1] + "," + arrayCadena[2] + "," + arrayCadena[3] + "," + arrayCadena[4] + "\n"
                        }
                    } while (opcionUpdate == true)
                    flag = true
                } else {
                    archivoUpdate += it + "\n"
                }
            }
            if (!flag) {
                println("LIBRERIA NO ENCONTRADA")
            } else {
                File("src/main/resources/text/libreria.txt").printWriter()
                    .use { out -> out.print(archivoUpdate) }
                println("LIBRERIA ACTUALIZADA")
            }
        }


        fun updateListaLibros(lista: String, id: Int): String {
            var newLista = ""
            var path = Paths.get("src/main/resources/text/libreria.txt")
            Files.lines(path, Charsets.UTF_8).forEach {
                var valorCadena = it.split(",")
                if (valorCadena[0].toInt() == id) {
                    for (i in 5..valorCadena.size - 1) {
                        if (i == valorCadena.size - 1) {
                            newLista += valorCadena[i]
                        } else {
                            newLista += valorCadena[i] + ","
                        }
                    }
                }
            }
            return newLista + "," + lista
        }


        fun deleteListaLibros(lista: String, id: Int): String {
            var newLista = ""
            var path = Paths.get("src/main/resources/text/libreria.txt")
            var splitListaParam = lista.split(",")
            Files.lines(path, Charsets.UTF_8).forEach {
                var valorCadena = it.split(",")
                if (valorCadena[0].toInt() == id) {
                    for (i in 5..valorCadena.size - 1) {
                        var bandera = false
                        for (j in 0..splitListaParam.size - 1) {
                            if (valorCadena[i] != splitListaParam[j]) {
                                bandera = true
                            } else {
                                bandera = false
                                break
                            }
                        }
                        if (bandera == true) {
                            newLista += valorCadena[i] + ","
                        }
                    }
                }
            }
            return removeLastNchars(newLista, 1)
        }

        fun removeLastNchars(str: String, n: Int): String {
            return str.substring(0, str.length - n)
        }


        fun deleteLibreria(nombreLibreria: String) {
            //Leer archivo
            var path = Paths.get("src/main/resources/text/libreria.txt")
            var flag = false
            var archivoUpdate = ""
            Files.lines(path, Charsets.UTF_8).forEach {
                var valorCadena = it.split(",")
                if (valorCadena[1] == nombreLibreria) {
                    println("Libreria ELIMINADA")
                    flag = true
                } else {
                    archivoUpdate += it + "\n"
                }
            }
            if (!flag) {
                println("LIBRERIA NO ENCONTRADA")
            } else {
                File("src/main/resources/text/libreria.txt").printWriter()
                    .use { out -> out.print(archivoUpdate) }
            }
        }


        fun getNumLibreria(): Int {
            var path = Paths.get("src/main/resources/text/libreria.txt")
            var numTotal = 0
            Files.lines(path, Charsets.UTF_8).forEach {
                numTotal += 1
            }
            return numTotal
        }


        fun setArrayListLibroLibreria(arrayLibros: Array<Int>): ArrayList<Libro> {
            var path = Paths.get("src/main/resources/text/libro.txt")
            var listaLibros: ArrayList<Libro> = ArrayList()
            var i = 0
            Files.lines(path, Charsets.UTF_8).forEach {
                var stringSplit = it.split(",")
                if (i < arrayLibros.size) {
                    if (stringSplit[0] == arrayLibros[i].toString()) {
                        var splitFecha = stringSplit[4].split("-")
                        var temaAux = Libro(
                            stringSplit[0].toInt(),
                            stringSplit[1],
                            stringSplit[2],
                            Date(
                                splitFecha[0].toInt(),
                                splitFecha[1].toInt(),
                                splitFecha[2].toInt()
                            ),
                            stringSplit[3].toDouble(),
                        )
                        listaLibros.add(temaAux)
                        i++
                    }
                }
            }
            return listaLibros
        }
    }


    fun insertLibreria(sizeArrayTemas: Int) {
        //Enviar al archivo
        var path = Paths.get("src/main/resources/text/libreria.txt")
        val formato = SimpleDateFormat("yyyy-MM-dd")
        var data =
            this.idLibreria.toString() + "," + this.nombreLibreria + "," + this.ubicacionLibreria + "," + formato.format(this.fechaCreacion) + "," + this.esPublica + ","
        var i = 1
        for (item in this.listaLibros!!) {
            if (i < sizeArrayTemas) {
                data += item.getIdLibro().toString() + ","
            } else {
                data += item.getIdLibro().toString()
            }
            i++
        }
        data += "\n"
        try {
            Files.write(path, data.toByteArray(), StandardOpenOption.APPEND)
            println("LIBRERIA AGREGADA\n")
        } catch (e: IOException) {
            println("Fallo al ingresar libreria al archivo")
        }
    }


}