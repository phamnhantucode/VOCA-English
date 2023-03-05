package com.phamnhantucode.vocaenglish.data.remote.api

import com.phamnhantucode.vocaenglish.data.remote.api.dto.QuoteDtoItem
import retrofit2.http.GET

interface QuoteApi {
    @GET("random")
    suspend fun getRandomQuote(): List<QuoteDtoItem>
}