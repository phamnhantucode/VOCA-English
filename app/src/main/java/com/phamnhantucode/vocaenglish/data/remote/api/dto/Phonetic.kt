package com.phamnhantucode.vocaenglish.data.remote.api.dto

data class Phonetic(
    val audio: String,
    val license: License,
    val sourceUrl: String,
    val text: String
)