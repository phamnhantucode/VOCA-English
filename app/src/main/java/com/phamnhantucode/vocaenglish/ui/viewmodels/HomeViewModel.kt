package com.phamnhantucode.vocaenglish.ui.viewmodels

import android.text.format.DateFormat
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phamnhantucode.vocaenglish.data.remote.api.dto.QuoteDtoItem
import com.phamnhantucode.vocaenglish.data.repositories.QuoteRepository
import com.phamnhantucode.vocaenglish.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.Calendar
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    val quoteRepository: QuoteRepository
) : ViewModel() {
    val current = mutableStateOf("")
    val quote = mutableStateOf(QuoteDtoItem("", ""))

    init {
        getRandomQuote()
        viewModelScope.launch {

            while (true) {
                current.value = DateFormat.format("EEEE, dd LLLL", Calendar.getInstance()).toString()
                delay(60000)
            }
        }
    }

    private fun getRandomQuote() {
        viewModelScope.launch {
            quoteRepository.getRandomQuote().collect {
                when (it) {
                    is Resource.Success -> {
                        quote.value = it.data!!
                    }
                    is Resource.Error -> {
                        Timber.e(it.message)
                    }
                    is Resource.Loading -> {
                        Timber.d("Loading")
                    }
                }
            }
        }
    }
}