package com.example.scrima.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.scrima.R
import com.example.scrima.entities.User
import com.example.scrima.general.Validators
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.io.Serializable


class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContentView(R.layout.activity_sign_up)
        setTitle("Crear una cuenta")
        // Initialize Firebase Auth
        val auth = FirebaseAuth.getInstance()
        findViewById<Button>(R.id.btn_signup)
            .setOnClickListener {
                // Get user inputs
                val userEmail = findViewById<EditText>(R.id.input_signup_email)
                val userPassword = findViewById<EditText>(R.id.input_signup_password)
                val userPasswordConfirmation = findViewById<EditText>(R.id.input_signup_repeat_password)
                if(Validators.validateNotBlankInputs(arrayListOf(userEmail, userPassword, userPasswordConfirmation))){
                    if (userPassword.text.toString() == userPasswordConfirmation.text.toString()){
                        auth.createUserWithEmailAndPassword(
                            userEmail.text.toString(),
                            userPasswordConfirmation.text.toString()
                        ).addOnCompleteListener{
                            if(it.isSuccessful){
                                showSimpleDialog("Inicio de sesión", "Se ha registrado correctamente")
                                openActivity(this, LogInActivity::class.java)
                            }else{
                                showSimpleToast("No se ha podido crear este usuario")
                            }
                        }
                    }
                    else{
                        showSimpleToast("Las contraseñas deben ser iguales")
                    }
                }else {
                    showSimpleToast("Ingrese los datos requeridos")
                }
            }
    }

    fun openActivity( context: Context, classRef: Class<*>) {
            val intentExplicito = Intent(
                context,
                classRef
            )
            startActivity(intentExplicito)
    }

    fun showSimpleDialog(title: String, message: String){
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(
                "Ok",
                null
            )
            .create()
            .show()
    }

    private fun showSimpleToast(message: String){
        Toast.makeText(
            this,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
}
