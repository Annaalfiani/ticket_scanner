package com.annaalfiani.ticketscanner.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Order(
    @SerializedName("id") var id : Int? = null,
    //@SerializedName("studio") var studio : Studio,
    //@SerializedName("film") var film : Movie,
    //@SerializedName("jadwal_tayang") var jadwal: Jadwal,
    @SerializedName("tanggal") var tanggal : String? = null,
    @SerializedName("jam") var jam : String ? = null,
    @SerializedName("harga") var harga : Int ? = null
    //@SerializedName("kursi") var seats : List<Seat> = mutableListOf()
) : Parcelable

@Parcelize
data class OrderDetails(
    @SerializedName("id") var id : Int? = null,
    @SerializedName("order") var order : Order
    //@SerializedName("kursi") var kursi : Seat
) : Parcelable