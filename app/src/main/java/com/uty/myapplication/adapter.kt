package com.uty.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.RelativeLayout
import android.widget.TextView

class adapter(context: Context, arrayListDetails:ArrayList<list>) : BaseAdapter(){

    private val layoutInflater: LayoutInflater
    private val arrayListDetails:ArrayList<list>

    init {
        this.layoutInflater = LayoutInflater.from(context)
        this.arrayListDetails=arrayListDetails
    }

    override fun getCount(): Int {
        return arrayListDetails.size
    }

    override fun getItem(position: Int): Any {
        return arrayListDetails.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val view: View?
        val listRowHolder: DViewHolder
        if (convertView == null) {
            view = this.layoutInflater.inflate(R.layout.list_data, parent, false)
            listRowHolder = DViewHolder(view)
            view.tag = listRowHolder
        } else {
            view = convertView
            listRowHolder = view.tag as DViewHolder
        }

        listRowHolder.Id.text = arrayListDetails.get(position).id.toString()
        listRowHolder.nm.text = arrayListDetails.get(position).nama
        listRowHolder.almt.text = arrayListDetails.get(position).alamat
        listRowHolder.naik.text = arrayListDetails.get(position).naik
        listRowHolder.turun.text = arrayListDetails.get(position).turun
        listRowHolder.telp.text = arrayListDetails.get(position).telp
        listRowHolder.gunung.text = arrayListDetails.get(position).gunung
        listRowHolder.rombongan.text = arrayListDetails.get(position).rombongan
        return view
    }
}

    private class DViewHolder(row: View?) {
        public val Id : TextView
        public val nm : TextView
        public val almt : TextView
        public val naik : TextView
        public val turun : TextView
        public val telp : TextView
        public val gunung : TextView
        public val rombongan : TextView


        init {
            this.Id = row?.findViewById<TextView>(R.id.tvID) as TextView
            this.nm = row?.findViewById<TextView>(R.id.tvNama_list) as TextView
            this.almt = row?.findViewById<TextView>(R.id.tvAlamat_list) as TextView
            this.naik = row?.findViewById<TextView>(R.id.tvNaik_list) as TextView
            this.turun = row?.findViewById<TextView>(R.id.tvTurun_list) as TextView
            this.telp = row?.findViewById<TextView>(R.id.tvTelp_list) as TextView
            this.gunung = row?.findViewById<TextView>(R.id.tvGunung_list) as TextView
            this.rombongan = row?.findViewById<TextView>(R.id.tvRombongan_list) as TextView

        }
}