package com.uty.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_peraturan.*

class peraturan : AppCompatActivity() {
    private lateinit var semeru : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peraturan)
        semeru = findViewById(R.id.tvsemeru)
        semeru.setOnClickListener {
            val smr = Intent (this, pembayaran_semeru::class.java)
            startActivity(smr)
        }
        var mTvrinjani = findViewById<TextView>(R.id.tvrinjani)
        mTvrinjani.setOnClickListener {
            startActivity(Intent(this, pembayaran_rinjani::class.java))
        }
    }
}
