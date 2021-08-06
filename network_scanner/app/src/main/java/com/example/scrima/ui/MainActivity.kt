package com.example.scrima.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.scrima.R
import com.example.scrima.general.Settings

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onClickToOpenActivity(R.id.btn_main_login, this, LogInActivity::class.java)
        onClickToOpenActivity(R.id.btn_main_signup, this, SignUpActivity::class.java)
    }

    fun onClickToOpenActivity(idButton: Int, context: Context, classRef: Class<*>){
        findViewById<Button>(idButton).setOnClickListener {
            val intentExplicito = Intent(
                context,
                classRef
            )
            startActivity(intentExplicito)
        }
    }

    fun openActivityWithoutParams(context : Context, clase: Class<*>){
        val intentExplicito = Intent(
            context,
            clase
        )
        startActivity(intentExplicito)
    }

}