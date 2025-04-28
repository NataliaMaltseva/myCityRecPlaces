package com.example.reccomendgoing.data.local

import com.example.reccomendgoing.R
import com.example.reccomendgoing.data.Place

object LocalPlacesDataProvider {
    val allPlaces = listOf(
        Place(
            id = 1,
            idCat = 1,
            name = R.string.place1_name,
            about = R.string.place1_about,
            address = R.string.place1_address,
            image = R.drawable.place_1
        ),
        Place(
            id = 2,
            idCat = 2,
            name = R.string.place4_name,
            about = R.string.place4_about,
            address = R.string.place4_address,
            image = R.drawable.place_4
        ),
        Place(
            id = 1,
            idCat = 1,
            name = R.string.place7_name,
            about = R.string.place7_about,
            address = R.string.place7_address,
            image = R.drawable.place_7
        ),
        Place(
            id = 1,
            idCat = 1,
            name = R.string.place11_name,
            about = R.string.place11_about,
            address = R.string.place11_address,
            image = R.drawable.place_11
        ),
        Place(
            id = 1,
            idCat = 1,
            name = R.string.place14_name,
            about = R.string.place14_about,
            address = R.string.place14_address,
            image = R.drawable.place_14
        ),
        Place(
            id = 1,
            idCat = 1,
            name = R.string.place17_name,
            about = R.string.place17_about,
            address = R.string.place17_address,
            image = R.drawable.place_17
        ),
    )

    val defaultPlace = allPlaces[0]
}