package com.phamnhantucode.vocaenglish.ui.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.phamnhantucode.vocaenglish.data.remote.api.dto.MeaningDto
import com.phamnhantucode.vocaenglish.domain.models.Word
import com.phamnhantucode.vocaenglish.ui.viewmodels.DictionaryViewModel
import com.phamnhantucode.vocaenglish.R

@Composable
fun DictionaryScreen(
    viewModel: DictionaryViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            val searchText by viewModel.searchText.collectAsState()
            val result by viewModel.word.collectAsState()
            val isSearching by viewModel.isSearching.collectAsState()

            BasicTextField(
                value = searchText, onValueChange = viewModel::onSearchText
            )

            if (isSearching) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            } else {
                if (result.isNotEmpty()) {
                    result.forEach {
                        WordCard(it)
                    }
                } else if (!searchText.isBlank()) {
                    NotFoundCard()
                }
            }
        }
    }
}

@Composable
fun WordCard(it: Word) {
    Text(text = it.word ?: "Not found")
}

@Composable
fun Meaning(
    meaning: MeaningDto
) {
    Text(text = meaning.partOfSpeech)
    Text(text = meaning.definitions.first().definition)
    for (synonym in meaning.synonyms) {
        Text(
            text = synonym
        )
    }
}

@Composable
fun NotFoundCard() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_notfound),
                contentDescription = stringResource(
                    id = R.string.not_found
                ),
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .aspectRatio(1f)
            )
            Text(
                text = stringResource(id = R.string.sorry),
                style = MaterialTheme.typography.h3,
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(id = R.string.no_definition_found),
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            Text(
                text = stringResource(id = R.string.message_no_definition_found),
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            Text(
                text = stringResource(id = R.string.resolution_no_definition_found),
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(0.8f)
            )
        }
    }
}