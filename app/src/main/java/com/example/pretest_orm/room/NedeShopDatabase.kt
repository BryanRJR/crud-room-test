package com.example.pretest_orm.room

import android.content.Context
import android.content.Entity
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pretest_orm.model.Nede

@Database(entities = [Nede::class], version = 2, exportSchema = false)
abstract class NedeShopDatabase : RoomDatabase() {

    abstract fun nedeDao(): NedeShopDao

    companion object {
        @Volatile
        private var INSTANCE: NedeShopDatabase? = null

        fun getDatabase(context: Context): NedeShopDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NedeShopDatabase::class.java,
                    "nede_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

        fun destroyDatabase(){
            INSTANCE = null
        }
    }

}