import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.text.SimpleDateFormat
import java.util.*

class Libro(
    //Atributos
    private var idLibro: Int,
    private var titulo: String,
    private var autor: String,
    private var anioPublicacion: Date,
    private var precio: Double
) {
    init {
        idLibro
        titulo
        autor
        anioPublicacion
        precio
    }

    fun setIdLibro(idLibro: Int) {
        this.idLibro = idLibro
    }

    fun setTitulo(titulo: String) {
        this.titulo = titulo
    }

    fun setAutor(autor: String) {
        this.autor = autor
    }

    fun setAnioPublicacion(anioPublicacion: Date) {
        this.anioPublicacion = anioPublicacion
    }

    fun setPrecio(precio: Double) {
        this.precio = precio
    }

    fun getIdLibro(): Int {
        return idLibro
    }

    fun getTitulo(): String {
        return titulo
    }

    fun getAutor(): String {
        return autor
    }

    fun getAnioPublicacion(): Date {
        return anioPublicacion
    }

    fun getPrecio(): Double {
        return precio
    }

    companion object {

        fun selectLibro() {
            // Leer archivo
            val path = Paths.get("src/main/resources/text/libro.txt")
            Files.lines(path, Charsets.UTF_8).forEach {
                val valorCadena = it.split(",")
                println(
                    "Núm. Libro: " + valorCadena[0] + "\n"
                            + "Título: " + valorCadena[1] + "\n"
                            + "Autor: " + valorCadena[2] + "\n"
                            + "Año Publicación: " + valorCadena[3] + "\n"
                            + "Precio: " + valorCadena[4] + "\n"
                )
            }
        }


        fun updateLibro(titulo: String) {
            // Leer archivo
            val path = Paths.get("src/main/resources/text/libro.txt")
            var flag = false
            var archivoUpdate = ""
            Files.lines(path, Charsets.UTF_8).forEach {
                val valorCadena = it.split(",")
                if (valorCadena[1] == titulo) {
                    var opcionUpdate = true
                    println(
                        "Núm. Libro: " + valorCadena[0] + "\n"
                                + "Título: " + valorCadena[1] + "\n"
                                + "Autor: " + valorCadena[2] + "\n"
                                + "Año Publicación: " + valorCadena[3] + "\n"
                                + "Precio: " + valorCadena[4] + "\n"
                    )
                    // Ver qué atributo desea modificar
                    val arrayCadena = arrayOf<String>("0", "0", "0", "0")
                    do {
                        println("Elija el atributo a modificar: 1) Título, 2) Autor, 3) Año Publicación, 4) Precio")
                        val atributoUpdate = readln().toInt()
                        when (atributoUpdate) {
                            1 -> {
                                print("Ingrese el nuevo título: ")
                                val titulo = readln()
                                arrayCadena[0] = titulo
                            }

                            2 -> {
                                print("Ingrese el nuevo autor: ")
                                val autor = readln()
                                arrayCadena[1] = autor
                            }

                            3 -> {
                                print("Ingrese la nueva fecha de publicación (AAAA-MM-DD): ")
                                val fecha = readln()
                                val auxFecha = fecha.split("-")
                                val newFecha: Date =
                                    Date(auxFecha[0].toInt() - 1900, auxFecha[1].toInt() - 1, auxFecha[2].toInt())
                                val formato = SimpleDateFormat("yyyy-MM-dd")
                                arrayCadena[2] = formato.format(newFecha)

                            }

                            4 -> {
                                print("Ingrese el nuevo precio: ")
                                val precio = readln()
                                arrayCadena[3] = precio
                            }
                        }
                        // Ver si desea seguir actualizando el libro elegido
                        println("¿Desea seguir actualizando el libro elegido? 1) SI - 2) NO")
                        val auxOpcion = readln().toInt()
                        if (auxOpcion == 2) {
                            opcionUpdate = false // Terminar update de libro
                            for (i in 0 until arrayCadena.size) {
                                if (arrayCadena[i] == "0") {
                                    arrayCadena[i] = valorCadena[i + 1]
                                }
                            }
                            archivoUpdate += valorCadena[0] + "," + arrayCadena[0] + "," + arrayCadena[1] + "," + arrayCadena[2] + "," + arrayCadena[3] + "\n"
                        }
                    } while (opcionUpdate)
                    flag = true
                } else {
                    archivoUpdate += it + "\n"
                }
            }
            if (!flag) {
                println("LIBRO NO ENCONTRADO")
            } else {
                File("src/main/resources/text/libro.txt").printWriter().use { out -> out.print(archivoUpdate) }
                println("LIBRO ACTUALIZADO")
            }
        }


        fun deleteLibro(titulo: String) {
            // Leer archivo
            val path = Paths.get("src/main/resources/text/libro.txt")
            var flag = false
            var archivoUpdate = ""
            Files.lines(path, Charsets.UTF_8).forEach {
                val valorCadena = it.split(",")
                if (valorCadena[1] == titulo) {
                    println("LIBRO ELIMINADO")
                    flag = true
                } else {
                    archivoUpdate += it + "\n"
                }
            }
            if (!flag) {
                println("LIBRO NO ENCONTRADO")
            } else {
                File("src/main/resources/text/libro.txt").printWriter().use { out -> out.print(archivoUpdate) }
            }
        }


        fun getNumLibros(): Int {
            val path = Paths.get("src/main/resources/text/libro.txt")
            var numTotal = 0
            Files.lines(path, Charsets.UTF_8).forEach {
                numTotal += 1
            }
            return numTotal
        }
    }


    fun insertLibro(){
        //Enviar al archivo
        val path = Paths.get("src/main/resources/text/libro.txt")
        val formato = SimpleDateFormat("yyyy-MM-dd")
        val data =
            "$idLibro,$titulo,$autor,${formato.format(anioPublicacion)},$precio\n"
        try {
            Files.write(path, data.toByteArray(), StandardOpenOption.APPEND)
            println("LIBRO AGREGADO")
        } catch (e: IOException) {
            println("Fallo al ingresar libro al archivo")
        }
    }
}