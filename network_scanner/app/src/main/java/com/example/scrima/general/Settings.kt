package com.example.scrima.general

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.scrima.ui.LogInActivity

class Settings  : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
    }

    fun onClickToOpenActivity(idButton: Int, context: Context, classRef: Class<LogInActivity>){
        findViewById<Button>(idButton).setOnClickListener {
            val intentExplicito = Intent(
                context,
                classRef
            )
            startActivity(intentExplicito)
        }
    }

    fun openActivityWithoutParams(context : Context, clase: Class<LogInActivity>){
        val intentExplicito = Intent(
            context,
            clase
        )
        startActivity(intentExplicito)
    }
}