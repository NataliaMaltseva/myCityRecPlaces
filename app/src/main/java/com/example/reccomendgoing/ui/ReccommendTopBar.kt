package com.example.reccomendgoing.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.reccomendgoing.R

@Composable
fun RecommendTopBar(
    modifier: Modifier = Modifier,
    title: String = "",
    onBackButtonClicked: () -> Unit,
    showBackArrow: Boolean = false
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (showBackArrow) {
            IconButton(
                onClick = onBackButtonClicked,
                modifier = modifier
                    .padding(start = dimensionResource(R.dimen.detail_topbar_back_button_padding_horizontal))
                    .size(dimensionResource(R.dimen.back_arrow_size))

            ) {
                Icon(
                    modifier = modifier
                                .background(MaterialTheme.colorScheme.surface,
                    shape = CircleShape
                ),
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.navigation_back)
                )
            }
        }

            Row(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Image(
                    modifier = modifier,
                    painter = painterResource(R.drawable.coffee_cat),
                    contentDescription = stringResource(R.string.app_name)
                )
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.bodyLarge
                )

            }
        }
}

@Preview
@Composable
fun ReccomendTopBarPreview() {
    RecommendTopBar(
        title = stringResource(R.string.app_name),
        onBackButtonClicked = {},
        showBackArrow = false
    )
}

@Preview
@Composable
fun ReccomendTopBarPreviewArrow() {
    RecommendTopBar(
        title = stringResource(R.string.app_name),
        onBackButtonClicked = {},
        showBackArrow = true
    )
}
