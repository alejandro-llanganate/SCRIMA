package com.example.scrima.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.scrima.R
import com.example.scrima.entities.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {

    // Initialize Firebase Auth
    val auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val user = intent.getParcelableExtra<User>("user")
        val  type = intent.getStringExtra("type")
        findViewById<TextView>(R.id.tv_email).setText(user.toString())
        findViewById<TextView>(R.id.tv_method).setText(type)

        findViewById<Button>(R.id.btn_sign_out).setOnClickListener {
            auth.signOut()
            onBackPressed()
        }
    }
}