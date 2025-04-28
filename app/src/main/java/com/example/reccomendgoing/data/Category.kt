package com.example.reccomendgoing.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Category(
    //   Unique ID of category
    val id: Long,

    //    Name of category
    @StringRes val name: Int,

    //    Image of category
    @DrawableRes
    val image: Int,

    //    Some text about category
    @StringRes val about: Int,
)
