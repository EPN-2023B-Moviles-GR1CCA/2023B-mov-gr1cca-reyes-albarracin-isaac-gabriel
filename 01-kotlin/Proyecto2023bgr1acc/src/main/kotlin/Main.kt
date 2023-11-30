import java.util.*
fun main() {
    println("Hola Mundo")
    //INMUTABLE (No se reasigna "=")
    val inmutable: String = "Isaac";
    // inmutable = "Isaac";
    //MUTABLES (Re asignar)
    var mutable: String = "Gabriel"


    //val > var , preferible usar val //Duck Typing, no es necesario definir
    var ejemploVariable = "Isaac Gabriel"
    val edadEjemplo: Int = 12
    ejemploVariable.trim()

    //Variable primitiva
    val nombreProfesor: String = "Adrian Eguez"
    val sueldo: Doble = 1.2
    val estadoCivil: Char = 'C'
    val mayorEdad: Boolean = true
    // clase JAVA
    val fechaNacimiento: Date = Date()


    //SWITCH, no existen como tal
    val estadoCivilWhen = "C"
    when (estadoCivilWhen) {
        ("C") -> {
            printin("Casado")
        }
        "S" -> {
            println("Soltero")
        }
        else -> {
            println("No sabemos")
        }
    }
    val coqueteo = if (estadoCivilWhen == "S") "Si" else "No"

    calcularSueldo(10.00)
    calcularSueldo(10.00, 15.00)
    calcularSueldo(10.00, 12.00, 20.00)
    //  Parametros nombrados
    calcularSueldo(sueldo = 10.00)
    calcularSueldo(sueldo = 10.00, tasa = 15.00)
    calcularSueldo(sueldo = 10.00, tasa = 12.00, bonoEspecial = 20.00)

    calcularSueldo(sueldo = 10.00, bonoEspecial = 20.00) // Named Parameters
    calcularSueldo(10.00, bonoEspecial = 20.00) // Named Parameters

    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00) //  Parametros nombrados


    val sumaUno = Suma(1,1)
    val sumaDos = Suma(null, 1)
    val sumaTres = Suma(1, null)


    }
    abstract class NumerosJava{
        protected val numeroUno: Int
        private val numeroDos: Int
        constructor(
            uno: Int,
            dos: Int
        ){ // Bloque de codigo del constructor
            this.numeroUno = uno
            this.numeroDos = dos
            println("Inicializando")
        }
    }
    abstract class Numeros( // Constructor PRIMARIO
        // Ejemplo:
        // unoProp: Int, // (Parametro (sin modificador de acceso))
        // private var uno: Int, // Propiedad Publica Clase numeros.uno
        // var uno: Int, // Propiedad de la clase (por defecto es PUBLIC)
        // public var uno: Int,
        // Propiedad de la clase protected numeros.numeroUno
        protected val numeroUno: Int,
        // Propiedad de la clase protected numeros.numeroDos
        protected val numeroDos: Int,
    ){
        // var cedula: string = "" (public es por defecto)
        // private valorCalculado: Int = 0 (private)
        init { // Bloque codigo constructor primario
            this.numeroUno; this.numeroDos; // this es opcional
            numeroUno; numeroDos; // sin el "this", es lo mismo
            println("Inicializando")
        }
    }

    class Suma( // Constructor Primario Suma
        uno: Int, // Parametro
        dos: Int // Parametro
    ): Numeros(uno, dos) { // <- Constructor del Padre
        init { // Bloque constructor primario
            this.numeroUno; numeroUno;
            this.numeroDos; numeroDos;
        }
        constructor(//  Segundo constructor
            uno: Int?, // parametros
            dos: Int // parametros
        ) : this(  // llamada constructor primario
            if (uno == null) 0 else uno,
            dos
        ) { // si necesitamos bloque de codigo lo usamos
            numeroUno;
        }
        constructor(//  tercer constructor
            uno: Int, // parametros
            dos: Int? // parametros
        ) : this(  // llamada constructor primario
            uno,
            if (dos == null) 0 else uno
        )
        // Si no lo necesitamos al bloque de codigo "{}" lo omitimos
        constructor(//  cuarto constructor
            uno: Int?, // parametros
            dos: Int? // parametros
        ) : this(  // llamada constructor primario
            if (uno == null) 0 else uno,
            if (dos == null) 0 else uno
        )


        // public por defecto, o usar private o protected
        public fun sumar(): Int {
            val total = numeroUno + numeroDos
            agregarHistorial(total)
            return total
        }
        companion object { // Atributos y Metodos "Compartidos"
            // entre las instancias
            val pi = 3.14
            fun elevarAlCuadrado(num: Int): Int {
                return num * num
            }
            val historialSumas = arrayListOf<Int>()
            fun agregarHistorial(valorNuevaSuma:Int){
                historialSumas.add(valorNuevaSuma)
            }
        }



    }


    // void -> Unit
    fun imprimirNombre(nombre: String): Unit{
        // "Nombre: " + variable + " bienvenido";
        println("Nombre : ${nombre}")
    }
    fun calcularSueldo(
        sueldo: Double, // Requerido
        tasa: Double = 12.00, // Opcional (defecto)
        bonoEspecial: Double? = null, // Opcion null -> nullable
    ): Double{
        // Int -> Int? (nullable)
        // String -> String? (nullable)
        // Date -> Date? (nullable)
        if(bonoEspecial == null){
            return sueldo * (100/tasa)
        }else{
            bonoEspecial.dec()
            return sueldo * (100/tasa ) + bonoEspecial
        }
    }