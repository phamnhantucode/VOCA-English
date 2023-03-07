package com.phamnhantucode.vocaenglish.data.remote.api

import com.phamnhantucode.vocaenglish.data.remote.api.dto.WordDto
import retrofit2.http.GET
import retrofit2.http.Path

interface WordApi {
    @GET("en/{word}")
    suspend fun getWord(
        @Path("word") word: String
    ): List<WordDto>

}