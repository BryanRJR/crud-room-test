package com.example.pretest_orm.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.fragment.findNavController
import com.example.pretest_orm.R
import com.example.pretest_orm.model.Nede
import com.example.pretest_orm.room.NedeShopDatabase
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_update.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class UpdateActivity : AppCompatActivity() {
    var mDb: NedeShopDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        mDb = NedeShopDatabase.getDatabase(this)

        val objectNede = intent.getParcelableExtra<Nede>("nedeshop")

        update_nama_barang.setText(objectNede!!.namaBarang)
        update_jumlah_barang.setText(objectNede.jumlahBarang.toString())
        update_harga_barang.setText(objectNede.hargaBarang.toString())

        btn_update.setOnClickListener {
            objectNede.namaBarang = update_nama_barang.text.toString()
            objectNede.jumlahBarang = update_jumlah_barang.text.toString().toInt()
            objectNede.hargaBarang = update_harga_barang.text.toString().toInt()

            GlobalScope.async {
                val result = mDb?.nedeDao()?.updateNede(objectNede)

                runOnUiThread {
                    if(result!=0){
                        Toast.makeText(this@UpdateActivity,"Sukses mengubah ${objectNede.namaBarang}", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this@UpdateActivity,"Gagal mengubah ${objectNede.namaBarang}", Toast.LENGTH_LONG).show()
                    }

                    finish()
                }
            }
        }
    }
}