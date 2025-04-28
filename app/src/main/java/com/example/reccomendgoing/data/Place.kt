package com.example.reccomendgoing.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Place(
    val id: Long,

    val idCat: Long,

    @StringRes
    val name: Int,

    @DrawableRes
    val image: Int,

    @StringRes
    val about: Int,

    @StringRes
    val address: Int
)
