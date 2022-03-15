package com.example.rickandmorty.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val info:Info,
    val results:List<Results>,

) : Parcelable