package com.example.scrima.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.scrima.R
import com.example.scrima.entities.User
import com.example.scrima.general.Validators
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LogInActivity : AppCompatActivity() {

    // Initialize Firebase Auth
    val auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        onClickToOpenActivity(R.id.btn_main_signup, this, SignUpActivity::class.java)
        findViewById<Button>(R.id.btn_main_login)
        .setOnClickListener {
            // Get user inputs
            val userEmail = findViewById<EditText>(R.id.input_login_email)
            val userPassword = findViewById<EditText>(R.id.input_login_password)
            if(Validators.validateNotBlankInputs(arrayListOf(userEmail, userPassword))){
                    auth.signInWithEmailAndPassword(
                        userEmail.text.toString(),
                        userPassword.text.toString()
                    ).addOnCompleteListener{
                        if(it.isSuccessful){
                            openActivityWithParams(this, HomeActivity::class.java, arrayListOf(
                                Pair("email", User(null, userEmail.text.toString(), userPassword.text.toString())),
                                Pair("type", ProviderType.BASIC)
                            ))
                        }else{
                            showSimpleToast("No se ha podido iniciar sesión correctamente")
                        }
                    }
            }else {
                showSimpleToast("Ingrese los datos requeridos")
            }
        }
    }

    private fun showSimpleToast(message: String){
        Toast.makeText(
            this,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    fun onClickToOpenActivity(idButton: Int, context: Context, classRef: Class<*>) {
        findViewById<Button>(idButton).setOnClickListener {
            val intentExplicito = Intent(
                context,
                classRef
            )
            startActivity(intentExplicito)
        }
    }

    fun openActivityWithParams(context: Context, classReference: Class<*>, params:  ArrayList<Pair<String, Any>>) {
        val intent = Intent(
            context,
            classReference
        )
        params.forEach {
                param ->
            var (key, value) = param

            if(value is User)
                intent.putExtra(key, value)
        }
        startActivity(intent)
    }
}
