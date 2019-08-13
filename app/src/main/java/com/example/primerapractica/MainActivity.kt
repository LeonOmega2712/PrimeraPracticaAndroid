package com.example.primerapractica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cargar()
        chbxLicenciatura.isChecked = true

        chbxPosgrado.setOnClickListener{
            if (chbxPosgrado.isChecked){
                chbxLicenciatura.isChecked = true
                chbxLicenciatura.isEnabled = false
            }
            if (chbxPosgrado.isChecked  == false){
                chbxLicenciatura.isEnabled = true
            }
        }

        btnMostrarInfo.setOnClickListener{
            var validado = validador()
            var estudio : String = ""
            var carrera = spLicenciaturas.selectedItem.toString()
            if (chbxPosgrado.isChecked){
                estudio = "posgrado"
            }
            else{
                estudio = "licenciatura"
            }

            if (validado == true) {
                lblInfo.text = "El correo del usuario es ${txtEmail.text.toString()}, tiene una edad de ${txtEdad.text.toString()} años, estudia el grado de $estudio con la carrera de $carrera"
            }
        }
    }

    fun cargar(){
        val listaElementos = arrayOf("Sistemas","Diseño", "Arquitectura")

        val adaptador = ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_item,
            listaElementos)

        spLicenciaturas.adapter  = adaptador
    }

    fun validador() : Boolean {

        var correoV : Boolean = false
        var edadV : Boolean = false

        var email = txtEmail.text.toString()
        var edad = txtEdad.text.toString()

        if (!email.contains("@") || !email.contains(".") || TextUtils.isEmpty(email)) {
            txtInputEmail.error = "Ingrese un correo válido"
        }
        else{
            txtInputEmail.isErrorEnabled = false
            correoV = true
        }

        if (TextUtils.isEmpty(edad)){
            txtInputEdad.error = "Ingrese una edad válida"
        }
        else {
            edadV = true
        }

        if (edadV == true && correoV == true) {
            return true
        }
        else {
            return false
        }
    }
}
