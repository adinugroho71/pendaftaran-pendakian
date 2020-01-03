package com.uty.myapplication

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_pendaftaran_rinjani.*
import kotlinx.android.synthetic.main.activity_pendaftaran_semeru.*
import org.json.JSONObject

class pendaftaran_semeru : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pendaftaran_semeru)

        imghome2.setOnClickListener {
            startActivity(Intent(this, home::class.java))
        }
        btn_simpan1.setOnClickListener {
            daftar()
        }
    }

    fun daftar() {
        val loading = ProgressDialog(this)
        loading.setMessage("Memuat data...")
        loading.show()

        AndroidNetworking.post(ApiEndPoint.daftar_fix)
            .addBodyParameter("nama_ketua", txtNama1.text.toString())
            .addBodyParameter("alamat", txtAlamat1.text.toString())
            .addBodyParameter("email", txtEmail1.text.toString())
            .addBodyParameter("tgl_lahir", txtLahir1.text.toString())
            .addBodyParameter("tanggal_naik", txtNaik1.text.toString())
            .addBodyParameter("tanggal_turun", txtTurun1.text.toString())
            .addBodyParameter("jumlah_rombongan", txtRombongan1.text.toString())
            .addBodyParameter("no_telp", txtTelp1.text.toString())
            .addBodyParameter("tempat_pendakian", txtPendakian1.text.toString())
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {
                    loading.dismiss()
                    Toast.makeText(
                        this@pendaftaran_semeru,
                        "Daftar sukses",
                        Toast.LENGTH_SHORT
                    ).show()

                    if (response?.getString("message")?.contains("successfully")!!) {
                        loading.dismiss()
                        this@pendaftaran_semeru.finish()
                    }


                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR", anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext, "Connection Failure", Toast.LENGTH_SHORT)
                        .show()
                }


            })
    }
}
