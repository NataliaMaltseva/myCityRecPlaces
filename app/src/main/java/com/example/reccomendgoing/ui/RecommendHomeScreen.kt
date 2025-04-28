package com.example.reccomendgoing.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reccomendgoing.data.Category
import com.example.reccomendgoing.data.Place

import com.example.reccomendgoing.R
import com.example.reccomendgoing.data.local.LocalCategoriesDataProvider

@Composable
fun RecommendHomeScreen(
    recommendsUIState: RecommendsUIState,
    onCategoryPressed: (Category) -> Unit,
    onPlacePressed: (Place) -> Unit,
    onBackToCategoryPressed: () -> Unit,
    onBackToPlacesListPressed: () -> Unit,
    modifier: Modifier = Modifier
) {

    if (recommendsUIState.isShowingPlacesList) {
        ReccomendPlacesListScreen(
            recommendsUIState = recommendsUIState,
            onPlacePressed = onPlacePressed,
            onBackToCategoryPressed = onBackToCategoryPressed,
            onBackToPlacesListPressed = onBackToPlacesListPressed,
            modifier = modifier
            )
    }
    else {
        RecommendCategoriesList(
            recommendsUIState = recommendsUIState,
            onCategoryPressed = onCategoryPressed,
            modifier = modifier
        )
    }
}

@Composable
fun RecommendCategoriesList(
    recommendsUIState: RecommendsUIState,
    onCategoryPressed: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    val categories = recommendsUIState.currentCategoriesList

    LazyColumn(
        modifier = modifier
    ) {
        item {
            RecommendTopBar(
                onBackButtonClicked = {},
                title = stringResource(R.string.app_name),
                showBackArrow = false
            )
        }
        items(categories) { category ->
            RecommendCategoryItem(
                category=category,
                onCategoryPressed = onCategoryPressed
                )
        }
    }
}

@Composable
fun RecommendCategoryItem(
    category: Category,
    onCategoryPressed: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(dimensionResource(R.dimen.category_list_item_outer_padding)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        onClick =  { onCategoryPressed(category) }
    ) {
        Row(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.category_list_item_inner_padding))
                .fillMaxWidth(1f)
        ){
            RecommendCategoryImage(
                drawableRes = category.image,
                description = stringResource(category.name)
                )

            Text(
                text = stringResource(category.name),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(
                    start = dimensionResource(R.dimen.category_list_item_name_spacing)
                )
            )
        }
    }
}

@Composable
fun RecommendCategoryImage(
    @DrawableRes drawableRes: Int,
    description: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.padding()
    ) {
        Image(
            modifier = Modifier.clip(CircleShape),
            painter = painterResource(drawableRes),
            contentDescription = description
        )
    }
}

@Preview
@Composable
fun RecommendsCategoryItem() {
    val category = LocalCategoriesDataProvider.defaultCategory
    RecommendCategoryItem(category=category, onCategoryPressed = {})
}

@Preview
@Composable
fun PreviewRecommendsCategoryList() {
    val viewModel: RecommendViewModel = viewModel()
    val recommendsUIState = viewModel.uiState.collectAsState().value
    RecommendCategoriesList(recommendsUIState = recommendsUIState, onCategoryPressed = {})
}