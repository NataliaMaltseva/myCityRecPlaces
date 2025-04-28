package com.example.reccomendgoing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.reccomendgoing.ui.RecommendApp
import com.example.reccomendgoing.ui.RecommendTopBar
import com.example.reccomendgoing.ui.theme.RecommendGoingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecommendGoingTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) {
                    innerPadding ->
                    RecommendApp(
                        modifier = Modifier
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RecommendGoingTheme {
        RecommendApp(
            modifier = Modifier
            .padding(dimensionResource(R.dimen.category_list_item_outer_padding))
        )
    }
}