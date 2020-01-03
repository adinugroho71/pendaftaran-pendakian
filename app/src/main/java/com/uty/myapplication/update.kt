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
import kotlinx.android.synthetic.main.activity_data_pendaki.*
import kotlinx.android.synthetic.main.activity_update.*
import org.json.JSONObject

class update : AppCompatActivity() {

    var id: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        load()
        Log.d("CEKK",id)

        btn_simpan_update.setOnClickListener {
            Update()
            startActivity(Intent(this, data_pendaki::class.java))
        }
    }

    fun load(){
        val loading = ProgressDialog(this)
        loading.setMessage("Memuat data...")
        loading.show()


        AndroidNetworking.get(ApiEndPoint.tampil_id)
            .addQueryParameter("id_pendaki",pass.id)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {

                    val jsonArray = response?.optJSONArray("data")

                    if(jsonArray?.length() == 0){
                        loading.dismiss()
                        Toast.makeText(applicationContext,"User tidak ditemukan", Toast.LENGTH_SHORT).show()
                        lvData.requestLayout()
                    }

                    for(i in 0 until jsonArray?.length()!!){
                        val jsonObject = jsonArray?.optJSONObject(i)


                                id = jsonObject.getInt("id_pendaki").toString()
                                txtNama_update.setText(jsonObject.getString("nama_ketua"))
                                txtAlamat_Update.setText(jsonObject.getString("alamat") )
                                txtNaik_update.setText(jsonObject.getString("tanggal_naik"))
                                txtTurun_update.setText(jsonObject.getString("tanggal_turun"))
                                txtTelp_Update.setText(jsonObject.getString("no_telp"))
                                txtPendakian_update.setText(jsonObject.getString("tempat_pendakian"))
                                txtRombongan_update.setText(jsonObject.getString("jumlah_rombongan"))


                        if(jsonArray?.length() - 1 == i){

                            loading.dismiss()
//                            val adapter = adapter(applicationContext,arrayList)
//                            adapter.notifyDataSetChanged()
//                            lvData.adapter = adapter

                        }

                    }

                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun Update(){
        AndroidNetworking.post(ApiEndPoint.ubah)
            .addBodyParameter("id_pendaki",id)
            .addBodyParameter("nama_ketua",txtNama_update.text.toString())
            .addBodyParameter("alamat", txtAlamat_Update.text.toString())
            .addBodyParameter("tanggal_naik", txtNaik_update.text.toString())
            .addBodyParameter("tanggal_turun", txtTurun_update.text.toString())
            .addBodyParameter("jumlah_rombongan", txtRombongan_update.text.toString())
            .addBodyParameter("no_telp", txtTelp_Update.text.toString())
            .addBodyParameter("tempat_pendakian", txtPendakian_update.text.toString())
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {


                    Toast.makeText(this@update, "Sukses ubah", Toast.LENGTH_SHORT).show()

                    if(response?.getString("message")?.contains("successfully")!!){
                        this@update.finish()
                    }


                }

                override fun onError(anError: ANError?) {
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()
                }

            })
    }
}
