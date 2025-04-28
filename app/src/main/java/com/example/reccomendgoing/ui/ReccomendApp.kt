package com.example.reccomendgoing.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.reccomendgoing.R
import com.example.reccomendgoing.data.Category
import com.example.reccomendgoing.data.Place

@Composable
fun RecommendApp (
    modifier: Modifier = Modifier
) {
    val viewModel: RecommendViewModel = viewModel()
    val recommendsUIState = viewModel.uiState.collectAsState().value

    RecommendHomeScreen(
        recommendsUIState = recommendsUIState,
        onCategoryPressed = { category: Category ->
            viewModel.updateCurrentPlacesList(selectedCategory = category)
        },
        onPlacePressed = { place: Place ->
            viewModel.updateCurrentPlaceScreenStates(selectedPlace = place)
        },
        onBackToCategoryPressed = {
            viewModel.resetCurrentPlacesListScreen()
        },
        onBackToPlacesListPressed = {
            viewModel.resetPlaceDetailsScreenStates()
        },
        modifier = modifier
    )
}
