package com.example.reccomendgoing.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
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

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.reccomendgoing.ui.theme.RecommendGoingTheme

import com.example.reccomendgoing.ui.utils.ReccomendsContentType
import com.example.reccomendgoing.ui.utils.ReccomendsNavigationType

enum class ReccomendScreen(@StringRes val title: Int) {
    Start(title=R.string.app_name),
    Places(title = R.string.choose_place),
    OnePlace(title = R.string.one_place)
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun RecommendApp (
    windowSize: WindowWidthSizeClass,
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

        val navigationType: ReccomendsNavigationType
        val contentType: ReccomendsContentType

        when (windowSize) {
            WindowWidthSizeClass.Compact -> {
                navigationType = ReccomendsNavigationType.BOTTOM_NAVIGATION
                contentType = ReccomendsContentType.LIST_ONLY
            }
            WindowWidthSizeClass.Medium -> {
                navigationType = ReccomendsNavigationType.NAVIGATION_RAIL
                contentType = ReccomendsContentType.LIST_ONLY
            }
            WindowWidthSizeClass.Expanded -> {
                navigationType = ReccomendsNavigationType.PERMANENT_NAVIGATION_DRAWER
                contentType = ReccomendsContentType.LIST_AND_DETAIL
            }
            else -> {
                navigationType = ReccomendsNavigationType.BOTTOM_NAVIGATION
                contentType = ReccomendsContentType.LIST_ONLY
            }
        }

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

@Preview(showBackground = true, widthDp = 700)
@Composable
fun GreetingPreviewMedium() {
    RecommendGoingTheme {
        RecommendApp(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.category_list_item_outer_padding)),
            windowSize = WindowWidthSizeClass.Medium
        )
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun GreetingPreviewExpand() {
    RecommendGoingTheme {
        RecommendApp(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.category_list_item_outer_padding)),
            windowSize = WindowWidthSizeClass.Expanded
        )
    }
}
