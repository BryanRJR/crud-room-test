package com.example.pretest_orm.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.Toast
import com.example.pretest_orm.R
import com.example.pretest_orm.model.Nede
import com.example.pretest_orm.room.NedeShopDatabase
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AddActivity : AppCompatActivity() {
    var mDb: NedeShopDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val jumlahBarang = et_jumlah_barang.text
        val hargaBarang = et_harga_barang.text

        mDb = NedeShopDatabase.getDatabase(this)

        btnSave.setOnClickListener {
            val objectNedeShop = Nede(
                0,
                et_nama_barang.text.toString(),
                Integer.parseInt(jumlahBarang.toString()),
                Integer.parseInt(hargaBarang.toString()),

            )

            GlobalScope.async {
                val result = mDb?.nedeDao()?.addNede(objectNedeShop)
                runOnUiThread {
                    if(result != 0.toLong() ){
                        //sukses
                        Toast.makeText(this@AddActivity,"Sukses menambahkan ${objectNedeShop.namaBarang}",
                            Toast.LENGTH_LONG).show()
                    }else{
                        //gagal
                        Toast.makeText(this@AddActivity,"Gagal menambahkan ${objectNedeShop.namaBarang}",
                            Toast.LENGTH_LONG).show()
                    }
                    finish()
                }
            }
        }
    }

}