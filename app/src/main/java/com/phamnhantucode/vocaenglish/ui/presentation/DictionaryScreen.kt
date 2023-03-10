package com.phamnhantucode.vocaenglish.ui.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.phamnhantucode.vocaenglish.data.remote.api.dto.MeaningDto
import com.phamnhantucode.vocaenglish.domain.models.Word
import com.phamnhantucode.vocaenglish.ui.viewmodels.DictionaryViewModel

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
                value = searchText
                , onValueChange = viewModel::onSearchText
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
                } else {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
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
    
}