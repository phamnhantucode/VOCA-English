package com.phamnhantucode.vocaenglish.ui.presentation

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.phamnhantucode.vocaenglish.data.remote.api.dto.MeaningDto
import com.phamnhantucode.vocaenglish.domain.models.Word
import com.phamnhantucode.vocaenglish.ui.viewmodels.DictionaryViewModel
import com.phamnhantucode.vocaenglish.R
import com.phamnhantucode.vocaenglish.ui.theme.DarkWhite
import kotlin.reflect.KFunction1

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

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 10.dp, top = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = stringResource(id = R.string.search),
                    tint = Color.Black,
                    modifier = Modifier.height(40.dp).aspectRatio(1f)
                )
                SearchBar(
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth(),
                    value = searchText, onValueChange = viewModel::onSearchText
                )
            }

            if (isSearching) {
                Box(modifier = Modifier.fillMaxSize()) {
                    GifImage(modifier = Modifier.align(Alignment.Center))
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

@Composable
fun GifImage(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = R.drawable.gif_finding).apply(block = {
                size(Size.ORIGINAL)
            }).build(), imageLoader = imageLoader
        ),
        contentDescription = null,
        modifier = modifier.fillMaxWidth(),
    )
}

@Preview("asd")
@Composable
fun aaa() {
    SearchBar(value = "", onValueChange = {})
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    value: String,
    backgroundTextFieldColor: Color = DarkWhite,
    onValueChange: (String) -> Unit,
) {
    BasicTextField(value = value, onValueChange = onValueChange, modifier = modifier) { innerTextField ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 5.dp)
                .clip(CircleShape)
                .background(
                    backgroundTextFieldColor
                )
                .padding(15.dp, 5.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.search),
                tint = MaterialTheme.colors.primary
            )
            innerTextField()
        }
    }
}