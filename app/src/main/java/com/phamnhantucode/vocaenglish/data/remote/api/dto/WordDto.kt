package com.phamnhantucode.vocaenglish.data.remote.api.dto

data class WordDto(
    val license: License? = null,
    val meanings: List<Meaning>? = null,
    val phonetics: List<Phonetic>? = null,
    val phonetic: String? = null,
    val sourceUrls: List<String>? = null,
    val word: String? = null
)