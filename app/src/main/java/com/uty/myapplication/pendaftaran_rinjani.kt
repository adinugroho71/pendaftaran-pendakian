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

class pendaftaran_rinjani : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pendaftaran_rinjani)

        imghome1.setOnClickListener {
            startActivity(Intent(this, home::class.java))
        }
        btn_simpan.setOnClickListener {
            daftar()
        }
    }
    fun daftar() {
        val loading = ProgressDialog(this)
        loading.setMessage("Memuat data...")
        loading.show()

        AndroidNetworking.post(ApiEndPoint.daftar_fix)
            .addBodyParameter("nama_ketua", txtNama.text.toString())
            .addBodyParameter("alamat", txtAlamat.text.toString())
            .addBodyParameter("email", txtEmail.text.toString())
            .addBodyParameter("tgl_lahir", txtLahir.text.toString())
            .addBodyParameter("tanggal_naik", txtNaik.text.toString())
            .addBodyParameter("tanggal_turun", txtTurun.text.toString())
            .addBodyParameter("jumlah_rombongan", txtRombongan.text.toString())
            .addBodyParameter("no_telp", txtTelp.text.toString())
            .addBodyParameter("tempat_pendakian", txtPendakian.text.toString())
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {
                    loading.dismiss()
                    Toast.makeText(
                        this@pendaftaran_rinjani,
                        "Daftar sukses",
                        Toast.LENGTH_SHORT
                    ).show()

                    if (response?.getString("message")?.contains("successfully")!!) {
                        loading.dismiss()
                        this@pendaftaran_rinjani.finish()
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
