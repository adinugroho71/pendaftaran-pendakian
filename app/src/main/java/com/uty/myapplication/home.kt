package com.uty.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        imgPeraturan.setOnClickListener {
            startActivity(Intent(this, peraturan::class.java))
        }

        imgPendaftaran.setOnClickListener {
            startActivity(Intent(this, daftar_gunung::class.java))
        }

        imgData.setOnClickListener {
            startActivity(Intent(this, data_pendaki::class.java))
        }
    }
}
