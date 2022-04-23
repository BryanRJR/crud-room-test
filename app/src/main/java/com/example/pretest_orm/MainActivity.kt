package com.example.pretest_orm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pretest_orm.activity.AddActivity
import com.example.pretest_orm.activity.CRUDActivity
import com.example.pretest_orm.adapter.NedeShopAdapter
import com.example.pretest_orm.room.NedeShopDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var mDB : NedeShopDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val crud : CardView = findViewById(R.id.menu_one)
        val menutwo : CardView = findViewById(R.id.menu_two)
        val menuthree : CardView = findViewById(R.id.menu_three)

        crud.setOnClickListener {
            startActivity(Intent(this, CRUDActivity::class.java))
        }
        menutwo.setOnClickListener {
            Toast.makeText(this, "MENU DALAM PEMBUATAN", Toast.LENGTH_LONG).show()
        }
        menuthree.setOnClickListener {
            Toast.makeText(this, "MENU DALAM PEMBUATAN", Toast.LENGTH_LONG).show()
        }

    }
}