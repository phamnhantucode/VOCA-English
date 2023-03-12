package com.phamnhantucode.vocaenglish.ui.presentation

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.phamnhantucode.vocaenglish.ui.viewmodels.HomeViewModel

@Composable
fun HomeScreen() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val verticalScrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(verticalScrollState),
        ) {
            TopBarHomeScreen(

            )
        }
    }
}

@Composable
fun TopBarHomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {

        val currentTime = remember {
            viewModel.current
        }

        val quote by remember {
            viewModel.quote
        }
        Text(text = "Hello Bảo đẹp trai")

        Text(text = currentTime.value, style = MaterialTheme.typography.h2)

        Text(
            text = quote.quote,
            style = MaterialTheme.typography.body1
        )
        Text(
            text = quote.author,
        )
    }
}