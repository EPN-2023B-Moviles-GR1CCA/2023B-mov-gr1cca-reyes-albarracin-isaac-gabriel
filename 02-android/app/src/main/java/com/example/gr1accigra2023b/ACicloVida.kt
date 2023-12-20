package com.example.gr1accigra2023b
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle

class ACicloVida : AppCompatActivity() {
    var textoGlobal=""

    fun mostrarSnackbar(texto:String){
        textoGlobal=textoGlobal + " " + texto
        Snackbar
            .make(
                findViewById(R.id.cl_ciclo_vida), //view
                textoGlobal, //texto
                Snackbar.LENGTH_INDEFINITE //tiempo
            )
            .show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aciclo_vida)
        mostrarSnackbar("OnCreate")
    }

    override fun onStart() {
        super.onStart()
        mostrarSnackbar("OnStart")
    }

    override fun onResume() {
        super.onResume()
        mostrarSnackbar("OnResume")
    }

    override fun onRestart() {
        super.onRestart()
        mostrarSnackbar("OnRestart")
    }

    override fun onPause() {
        super.onPause()
        mostrarSnackbar("OnPause")
    }

    override fun onStop() {
        super.onStop()
        mostrarSnackbar("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        mostrarSnackbar("onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run{
            //GUARDAR VARIABLES
            //PRIMITIVOS
            putString("textoGuardado", textoGlobal)
            //putInt("numeroGuardado",numero)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        //Recuperar las variables
        //primitivos
        val textoRecuperado: String? = savedInstanceState.getString("textoGuardado")
        //val textoRecuperado:Int?=savedInstanceState.getInt("numeroGuardado")
        if (textoRecuperado != null) {
            mostrarSnackbar(textoRecuperado)
            textoGlobal = textoRecuperado
        }
    }




}