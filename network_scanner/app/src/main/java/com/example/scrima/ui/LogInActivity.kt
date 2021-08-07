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
        onClickToOpenActivity(R.id.btn_main_signup, this, LogInActivity::class.java)

    }

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
                        openActivityWithoutParams(this, HomeActivity::class.java, arrayListOf(
                            Pair("email", User(null, userEmail.text.toString(), userPassword.text.toString())),
                            Pair("type", ProviderType.BASIC)
                        ))
                        showSimpleDialog("Inicio de sesión", "Se ha registrado correctamente")
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

fun onClickToOpenActivityWithParams(idButton: Int, context: Context, classRef: Class<*>) {
    findViewById<Button>(idButton).setOnClickListener {
        val intent = Intent(
            context,
            classRef
        )
        startActivity(intent)
    }
}

fun openActivityWithoutParams(context: Context, classReference: Class<*>, params:  ArrayList<Pair<String, Any>>) {
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
