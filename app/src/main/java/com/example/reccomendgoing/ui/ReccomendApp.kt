package com.example.reccomendgoing.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import com.example.reccomendgoing.R
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.reccomendgoing.data.Category

enum class ReccomendScreen(@StringRes val title: Int) {
    Start(title=R.string.app_name),
    Places(title = R.string.choose_place),
    OnePlace(title = R.string.one_place)
}

@Composable
fun RecommendApp (
    updateCurCategory: (Category, RecommendViewModel) -> Unit,
    navController: NavHostController = rememberNavController()
) {
    val viewModel: RecommendViewModel = viewModel()
    val recommendsUIState = viewModel.uiState.collectAsState().value

    Scaffold(
        topBar = {
            RecommendTopBar(
                onBackButtonClicked =  { navController.navigateUp() },
                canNavigateBack = navController.previousBackStackEntry != null,
                title = stringResource(R.string.app_name)
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ReccomendScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ){

//        Домашний экран. Список категорий
            composable(
                route=ReccomendScreen.Start.name
            ) {
                RecommendHomeScreen(
                    recommendsUIState = recommendsUIState,
                    onCategoryPressed = {
                        navController.navigate(ReccomendScreen.Places.name)
                    },
                    updateCurCategory = updateCurCategory,
                    modifier = Modifier.padding(innerPadding)
                )
            }

// Список мест выбранной категории
            composable(route = ReccomendScreen.Places.name) {
                ReccomendPlacesListScreen(
                    recommendsUIState = recommendsUIState,
                    onPlacePressed = {navController.navigate(ReccomendScreen.OnePlace.name)},
                    modifier = Modifier.padding(innerPadding)
                )
            }

//        Одно место
            composable(route = ReccomendScreen.OnePlace.name) {
                ReccommendPlaceScreen(
                    uiState = recommendsUIState,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@Composable
fun updateCurCategory(category: Category, viewModel: RecommendViewModel) {
    viewModel.updateCurrentPlacesList(selectedCategory = category)
}
