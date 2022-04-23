package com.example.pretest_orm.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pretest_orm.model.Nede

@Dao
interface NedeShopDao {

    @Query("SELECT * FROM Nede")
    fun getAllNede(): List<Nede>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addNede(nede: Nede):Long

    @Update
    fun updateNede(nede: Nede):Int

    @Delete
    fun deleteNede(nede: Nede):Int

    @Query("DELETE FROM Nede")
    fun deleteAllNede():Int
//
//    @Query("SELECT * FROM nede_table ORDER BY id ASC")
//    fun readAllData(): LiveData<List<Nede>>

}