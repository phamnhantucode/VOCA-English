package com.phamnhantucode.vocaenglish.data.repositories

import com.phamnhantucode.vocaenglish.data.remote.api.dto.QuoteDtoItem
import com.phamnhantucode.vocaenglish.utils.Resource
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {
    suspend fun getRandomQuote(): Flow<Resource<QuoteDtoItem>>
}