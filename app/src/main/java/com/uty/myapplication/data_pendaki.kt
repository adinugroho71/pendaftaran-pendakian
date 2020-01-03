package com.uty.myapplication

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_data_pendaki.*
import org.json.JSONObject

class data_pendaki : AppCompatActivity() {

    var arrayList = ArrayList<list>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_pendaki)

        load()

        lvData.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val id = (view?.findViewById<View>(R.id.tvID) as TextView).text.toString()
            Log.d("CEK",id)
            pass.setId(id)
            startActivity(Intent(this, update::class.java))
        }

    }

    fun load(){
        val loading = ProgressDialog(this)
        loading.setMessage("Memuat data...")
        loading.show()


        AndroidNetworking.get(ApiEndPoint.tampil)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {
                    arrayList.clear()

                    val jsonArray = response?.optJSONArray("data")

                    if(jsonArray?.length() == 0){
                        loading.dismiss()
                        Toast.makeText(applicationContext,"User tidak ditemukan", Toast.LENGTH_SHORT).show()
                        lvData.requestLayout()
                    }

                    for(i in 0 until jsonArray?.length()!!){
                        val jsonObject = jsonArray?.optJSONObject(i)

                        arrayList.add(
                            list(jsonObject.getInt("id_pendaki"),
                                jsonObject.getString("nama_ketua"),
                                jsonObject.getString("alamat"),
                                jsonObject.getString("tanggal_naik"),
                                jsonObject.getString("tanggal_turun"),
                                jsonObject.getString("no_telp"),
                                jsonObject.getString("tempat_pendakian"),
                                jsonObject.getString("jumlah_rombongan"))
                        )

                        if(jsonArray?.length() - 1 == i){

                            loading.dismiss()
                            val adapter = adapter(applicationContext,arrayList)
                            adapter.notifyDataSetChanged()
                            lvData.adapter = adapter

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
}
