package com.example.reccomendgoing.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.example.reccomendgoing.R
import com.example.reccomendgoing.data.Category
import com.example.reccomendgoing.data.Place
import com.example.reccomendgoing.ui.theme.RecommendGoingTheme

@Composable
fun ReccommendPlaceScreen(
    uiState: RecommendsUIState,
    modifier: Modifier = Modifier
) {
    val currentPlace = uiState.currentPlace
    val currentCategory = uiState.currentSelectedCategory
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.one_place_padding_content)),
        verticalArrangement = Arrangement.Center
    ) {

        ReccommendPlaceScreenHead(
            modifier = modifier,
            currentCategory = currentCategory,
            currentPlace = currentPlace
        )
        Spacer(
            modifier = Modifier.padding(dimensionResource(R.dimen.one_place_padding_spacer))
        )

        ReccomendPlaceImages(
                placeImage = currentPlace.image,
        placeDesc = currentPlace.name
        )
        Spacer(
            modifier = Modifier.padding(dimensionResource(R.dimen.one_place_padding_spacer))
        )

        Text(
            modifier = modifier,
            text = stringResource(currentPlace.about),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun ReccommendPlaceScreenHead(
    modifier: Modifier,
    currentCategory: Category,
    currentPlace: Place
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        ReccommendCategoryIcon(
            modifier = modifier,
            categoryIcon = currentCategory.image,
            descCat = stringResource(currentCategory.name)
        )

        Column(
            modifier = modifier
                .padding(start = dimensionResource(R.dimen.one_place_padding_name_category))
        ) {
            Text(
                modifier = modifier,
                text = stringResource(currentPlace.name),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.tertiary
            )

            Text(
                modifier = modifier,
                text = stringResource(currentPlace.address),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
fun ReccommendCategoryIcon(
    modifier: Modifier = Modifier,
    categoryIcon: Int,
    descCat: String?
) {
    Card(
        modifier = modifier
            .padding(start = dimensionResource(R.dimen.one_place_padding_icon_category))
    ) {
        Image(
            modifier = modifier,
            painter = painterResource(categoryIcon),
            contentDescription = descCat
        )
    }
}

@Composable
fun ReccomendPlaceImages(
    placeImage: Int,
    placeDesc: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(placeImage),
            contentDescription = stringResource(placeDesc),
            modifier = Modifier
                .size(dimensionResource(R.dimen.one_place_image_size))
                .clip(RoundedCornerShape(dimensionResource(R.dimen.one_place_image_round_corner)))
        )
    }

}

@Preview
@Composable
fun ReccommendPlacePreview(

) {
    RecommendGoingTheme {
        val viewModel: RecommendViewModel = viewModel()
        val recommendsUIState = viewModel.uiState.collectAsState().value
        ReccommendPlaceScreen(
            uiState = recommendsUIState
        )
    }
}
