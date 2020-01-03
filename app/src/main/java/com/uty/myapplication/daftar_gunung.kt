package com.uty.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_daftar_gunung.*

class daftar_gunung : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_gunung)

        imageView7.setOnClickListener {
            startActivity(Intent(this, pendaftaran_semeru::class.java))
        }

        imageView8.setOnClickListener {
            startActivity(Intent(this, pendaftaran_rinjani::class.java))
        }
    }
}
