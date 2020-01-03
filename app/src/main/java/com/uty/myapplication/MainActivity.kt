package com.uty.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_login.setOnClickListener {
            if(txtUsername.text.toString() == "pendakian" && txtPassword.text.toString() == "pendakian"){
                startActivity(Intent(this, home::class.java))
            }else{
                Toast.makeText(this,"Username atau Password Salah", Toast.LENGTH_SHORT).show()
            }

        }
    }
}
