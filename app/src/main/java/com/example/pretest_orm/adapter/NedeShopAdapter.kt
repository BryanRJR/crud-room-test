package com.example.pretest_orm.adapter

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pretest_orm.MainActivity
import com.example.pretest_orm.R
import com.example.pretest_orm.activity.CRUDActivity
import com.example.pretest_orm.activity.UpdateActivity
import com.example.pretest_orm.model.Nede
import com.example.pretest_orm.room.NedeShopDatabase
import kotlinx.android.synthetic.main.nedeshop_item.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class NedeShopAdapter(val nedeList : List<Nede>) : RecyclerView.Adapter<NedeShopAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.nedeshop_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return nedeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tv_id.text = nedeList[position].id.toString()
        holder.itemView.tv_nama.text = nedeList[position].namaBarang
        holder.itemView.tv_jumlah.text = nedeList[position].jumlahBarang.toString()
        holder.itemView.tv_harga.text = nedeList[position].hargaBarang.toString()

        holder.itemView.ivEdit.setOnClickListener {
            val intentKeEditActivity = Intent(it.context,
                UpdateActivity::class.java)

            intentKeEditActivity.putExtra("nedeshop",nedeList[position])
            it.context.startActivity(intentKeEditActivity)
        }

        holder.itemView.ivDelete.setOnClickListener {
            AlertDialog.Builder(it.context).setPositiveButton("Ya") { p0, p1 ->
                val mDb = NedeShopDatabase.getDatabase(holder.itemView.context)

                GlobalScope.async {
                    val result = mDb?.nedeDao()?.deleteNede(nedeList[position])

                    (holder.itemView.context as CRUDActivity).runOnUiThread {
                        if (result!=0){
                            Toast.makeText(it.context,"Data ${nedeList[position].namaBarang} berhasil dihapus",
                                Toast.LENGTH_LONG).show()
                        }else{
                            Toast.makeText(it.context,"Data ${nedeList[position].namaBarang} Gagal dihapus",
                                Toast.LENGTH_LONG).show()
                        }
                    }

                    (holder.itemView.context as CRUDActivity).fetchData()
                }
            }.setNegativeButton("Tidak"
            ) { p0, p1 ->
                p0.dismiss()
            }
                .setMessage("Apakah Anda Yakin Ingin Menghapus Data ${nedeList[position].namaBarang}").setTitle("Konfirmasi Hapus").create().show()
        }
    }
}