package com.example.reccomendgoing.ui

import androidx.lifecycle.ViewModel
import com.example.reccomendgoing.data.Category
import com.example.reccomendgoing.data.Place
import com.example.reccomendgoing.data.local.LocalCategoriesDataProvider
import com.example.reccomendgoing.data.local.LocalPlacesDataProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RecommendViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(RecommendsUIState())
    val uiState: StateFlow<RecommendsUIState> = _uiState

    init {
        initializationUIState()
    }

    private fun initializationUIState() {
        val categoryPlaces: Map<Long, List<Place>> =
            LocalPlacesDataProvider.allPlaces.groupBy { it.idCat }

        _uiState.value =
            RecommendsUIState(
                categoryPlaces = categoryPlaces,
                currentSelectedCategory = LocalCategoriesDataProvider.get(1)?:LocalCategoriesDataProvider.defaultCategory,
                currentCategoriesList = LocalCategoriesDataProvider.allCategories
            )
    }

//    при нажатии на пункт списка Места
    fun updateCurrentPlaceScreenStates(selectedPlace: Place) {
        _uiState.update {
            it.copy(

                currentPlace = selectedPlace,
                isShowingPlaceScreen = true
            )
        }
    }

    fun resetPlaceDetailsScreenStates() {
        _uiState.update {
            it.copy(
                isShowingPlaceScreen = false
            )
        }
    }

    fun updateCurrentPlacesList(selectedCategory: Category) {
        _uiState.update {
            it.copy(
                currentSelectedCategory = selectedCategory,
                isShowingPlacesList = true
            )
        }
    }

    fun resetCurrentPlacesListScreen() {
        _uiState.update {
            it.copy(
                currentSelectedCategory = LocalCategoriesDataProvider.defaultCategory,
                isShowingPlacesList = false
            )
        }
    }


}
