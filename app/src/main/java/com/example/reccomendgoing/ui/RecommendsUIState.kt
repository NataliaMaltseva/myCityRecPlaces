package com.example.reccomendgoing.ui

import com.example.reccomendgoing.data.Category
import com.example.reccomendgoing.data.Place
import com.example.reccomendgoing.data.local.LocalCategoriesDataProvider
import com.example.reccomendgoing.data.local.LocalPlacesDataProvider

data class RecommendsUIState(
    val categoryPlaces: Map<Long, List<Place>> = emptyMap(),
    val currentSelectedCategory: Category = LocalCategoriesDataProvider.defaultCategory,
    val currentCategoriesList: List<Category> = LocalCategoriesDataProvider.allCategories,
    val currentPlace: Place = LocalPlacesDataProvider.defaultPlace,
    val isShowingPlaceScreen: Boolean = true,
    val isShowingPlacesList: Boolean = false
) {
    val currentCategoryPlaces: List<Place> by lazy {categoryPlaces[currentSelectedCategory.id]!!}
}

