package com.example.pretest_orm.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Nede(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "nama") var namaBarang: String,
    @ColumnInfo(name = "jumlah") var jumlahBarang: Int,
    @ColumnInfo(name = "harga")var hargaBarang: Int
): Parcelable