package com.uty.myapplication


class ApiEndPoint {

    companion object {
        private val SERVER = "http://10.0.2.2/pendaftaran/"
        val daftar_fix = SERVER+"daftar_fix.php"
        val tampil = SERVER+"tampil.php"
        val tampil_id = SERVER+"tampil_id.php"
        val ubah = SERVER+ "ubah.php"
    }

}