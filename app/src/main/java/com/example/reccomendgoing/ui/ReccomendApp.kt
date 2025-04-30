package com.example.reccomendgoing.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentCompositionErrors
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.example.reccomendgoing.R
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.reccomendgoing.ui.RecommendTopBar

enum class ReccomendScreen(@StringRes val title: Int) {
    Start(title=R.string.app_name),
    Places(title = R.string.choose_place),
    OnePlace(title = R.string.one_place)
}

@Composable
fun RecommendApp (
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val viewModel: RecommendViewModel = viewModel()
    val recommendsUIState = viewModel.uiState.collectAsState().value

    // ссылка для кнопки назад
    val backStackEntry by navController.currentBackStackEntryAsState()

    //    имя текущего экрана
    val currentScreen = ReccomendScreen.valueOf(
        backStackEntry?.destination?.route?:ReccomendScreen.Start.name
    )

    Scaffold(
        topBar = {
            RecommendTopBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry!=null,
                navigateUp = {
                    navController.navigateUp()
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ReccomendScreen.Start.name,
            modifier = modifier.padding(innerPadding)
        ) {

//        Домашний экран. Список категорий
            composable(
                route = ReccomendScreen.Start.name
            ) {
                RecommendHomeScreen(
                    recommendsUIState = recommendsUIState,
                    onCategoryPressed = {
                        navController.navigate(ReccomendScreen.Places.name)
                    },
                    viewModel = viewModel,
                    modifier = modifier
                )
            }

// Список мест выбранной категории
            composable(route = ReccomendScreen.Places.name) {
                ReccomendPlacesListScreen(
                    recommendsUIState = recommendsUIState,
                    viewModel = viewModel,
                    onPlacePressed = {
                        navController.navigate(ReccomendScreen.OnePlace.name)
                    },
                    modifier = modifier
                )
            }

//        Одно место
            composable(route = ReccomendScreen.OnePlace.name) {
                ReccommendPlaceScreen(
                    uiState = recommendsUIState,
                    modifier = modifier
                )
            }
        }
    }
}
