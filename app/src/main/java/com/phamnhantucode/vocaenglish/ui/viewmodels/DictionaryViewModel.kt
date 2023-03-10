package com.phamnhantucode.vocaenglish.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phamnhantucode.vocaenglish.data.remote.api.dto.WordDto
import com.phamnhantucode.vocaenglish.data.repositories.WordRepository
import com.phamnhantucode.vocaenglish.domain.models.Word
import com.phamnhantucode.vocaenglish.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DictionaryViewModel @Inject constructor(
    val wordRepository: WordRepository
) :
    ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _result = MutableStateFlow(ArrayList<Word>())
    val word = _result.asStateFlow()
//    val word = searchText
//        .debounce(100L) //delay 100milliseconds
//        .onEach {
//            _isSearching.update { true }
//        }
//        .combine(_result) { text, result ->
//            if (!text.isBlank() && searchWord(text).isCompleted) {
//                _result.value
//            } else {
//                WordDto(word = "")
//            }
//        }
//        .onEach { _isSearching.update { false } }
//        .stateIn(
//            viewModelScope,
//            SharingStarted.WhileSubscribed(5000),
//            _result.value
//        )

    private fun searchWord() =
        viewModelScope.launch {
            wordRepository.findWord(_searchText.value).collect {result ->
                when (result) {
                    is Resource.Success -> {
                        _result.update { (result.data as ArrayList<Word>?)!! }
                    }
                    is Resource.Error -> {
                        Timber.e(result.message)
                        _result.update { ArrayList() }
                    }
                    else -> {
                        _result.update { ArrayList() }
                    }
                }
            }
        }
    fun onSearchText(text: String) {
        _searchText.value = text
        searchWord()
    }


}

