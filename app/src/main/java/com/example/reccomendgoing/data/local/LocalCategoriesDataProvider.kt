package com.example.reccomendgoing.data.local

import android.annotation.SuppressLint
import com.example.reccomendgoing.data.Category
import com.example.reccomendgoing.R


object LocalCategoriesDataProvider {
    @SuppressLint("ResourceType")
    val allCategories = listOf(
        Category(
            id = 1,
            name = R.string.category1_name,
            about = R.string.category1_about,
            image = R.drawable.coffee_cat
        ),
        Category(
            id = 2,
            name = R.string.category2_name,
            about = R.string.category2_about,
            image = R.drawable.cafe
        ),
        Category(
            id = 3,
            name = R.string.category3_name,
            about = R.string.category3_about,
            image = R.drawable.cheese
        ),
        Category(
            id = 4,
            name = R.string.category4_name,
            about = R.string.category4_about,
            image = R.drawable.kids
        ),
        Category(
            id = 5,
            name = R.string.category5_name,
            about = R.string.category5_about,
            image = R.drawable.park
        ),
        Category(
            id = 6,
            name = R.string.category6_name,
            about = R.string.category6_about,
            image = R.drawable.sport
        )
    )

    val defaultCategory = allCategories[0]

    fun get(id: Long): Category? {
        return  allCategories.firstOrNull { it.id == id }
    }
}


