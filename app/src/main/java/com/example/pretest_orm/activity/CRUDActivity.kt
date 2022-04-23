package com.example.pretest_orm.activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pretest_orm.MainActivity
import com.example.pretest_orm.R
import com.example.pretest_orm.adapter.NedeShopAdapter
import com.example.pretest_orm.model.Nede
import com.example.pretest_orm.room.NedeShopDatabase
import kotlinx.android.synthetic.main.activity_crudactivity.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nedeshop_item.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class CRUDActivity : AppCompatActivity() {
    private var myNedeDB : NedeShopDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crudactivity)

        myNedeDB = NedeShopDatabase?.getDatabase(this)

        // Recyclerview
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        fetchData()

        btn_clear_data.setOnClickListener {
            deleteAllUsers()
        }

    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    fun fetchData(){
        GlobalScope.launch {
            val nedeList = myNedeDB?.nedeDao()?.getAllNede()

            runOnUiThread{
                nedeList?.let {
                    val adapter = NedeShopAdapter(it)
                    recyclerView.adapter = adapter
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.menu_add) {
            val intent = Intent(
                this, AddActivity::class.java)
            startActivity (intent)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsers() {
        GlobalScope.async {
            val result = myNedeDB?.nedeDao()?.deleteAllNede()
            runOnUiThread {
                if(result != 0 ){
                    //sukses
                    Toast.makeText(this@CRUDActivity,"Sukses Menghapus Semua",
                        Toast.LENGTH_LONG).show()
                }else{
                    //gagal
                    Toast.makeText(this@CRUDActivity,"Gagal Menghapus Semua",
                        Toast.LENGTH_LONG).show()
                }
                finish()
            }
        }
    }

}