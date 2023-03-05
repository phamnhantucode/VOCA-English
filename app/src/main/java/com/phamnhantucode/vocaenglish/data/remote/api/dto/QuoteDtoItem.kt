package com.phamnhantucode.vocaenglish.data.remote.api.dto

import com.squareup.moshi.Json

data class QuoteDtoItem(
    @field:Json(name = "a")
    val author: String,
    @field:Json(name = "q")
    val quote: String
)