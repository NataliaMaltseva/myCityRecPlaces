package com.example.reccomendgoing.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import com.example.reccomendgoing.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reccomendgoing.data.Place
import com.example.reccomendgoing.data.local.LocalPlacesDataProvider

@Composable
fun ReccomendPlacesListScreen(
    recommendsUIState: RecommendsUIState,
    viewModel: RecommendViewModel,
    onPlacePressed: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    ReccomendPlacesList(
        recommendsUIState = recommendsUIState,
        viewModel = viewModel,
        onPlacePressed = onPlacePressed,
        modifier = modifier
    )
}

@Composable
fun ReccomendPlacesList(
    recommendsUIState: RecommendsUIState,
    viewModel: RecommendViewModel,
    onPlacePressed: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    val categoryPlaces = recommendsUIState.currentCategoryPlaces

    LazyColumn(
        modifier = modifier
    ) {
        items(categoryPlaces) { placeItem ->
            ReccommendPlaceItem(
                viewModel = viewModel,
                onPlacePressed = onPlacePressed,
                placeItem = placeItem
            )
        }
    }
}

@Composable
fun ReccommendPlaceItem(
    viewModel: RecommendViewModel,
    onPlacePressed: (Place) -> Unit,
    placeItem: Place
) {
    Card(
        modifier = Modifier
            .padding(
            bottom = dimensionResource(R.dimen.place_list_outer_padding)
        ),
        onClick = {
            viewModel.updateCurrentOnePlace(selectedPlace = placeItem)
            onPlacePressed(placeItem)
        }
    ){
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PlaceItemImage(
                placeImageId = placeItem.image,
                description = stringResource(placeItem.name)
            )
            Text(
                text = stringResource(placeItem.name),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun PlaceItemImage(
    @DrawableRes placeImageId: Int,
    description: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(dimensionResource(R.dimen.place_list_outer_padding)),
    ) {
        Image(
            modifier = Modifier
                .clip(CircleShape)
                .size(dimensionResource(R.dimen.place_list_image_size)),
            painter = painterResource(placeImageId),
            contentDescription = description
        )
    }
}

@Preview
@Composable
fun PreviewPlaceItem() {
    val placeDefault = LocalPlacesDataProvider.defaultPlace
    val viewModel: RecommendViewModel = viewModel()

    ReccommendPlaceItem(
        onPlacePressed = {},
        viewModel = viewModel,
        placeItem = placeDefault
    )
}

@Preview
@Composable
fun PreviewPlaceList() {
    val viewModel: RecommendViewModel = viewModel()
    val recommendsUIState = viewModel.uiState.collectAsState().value

    ReccomendPlacesList(
        onPlacePressed = {},
        recommendsUIState = recommendsUIState,
        viewModel = viewModel
    )
}
