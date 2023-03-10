package com.phamnhantucode.vocaenglish.domain.models

data class Meaning(
    val antonyms: List<String>? = null,
    val synonyms: List<String>? = null,
    val partOfSpeech: String? = null,
    val definitions: List<Definition>? = null
)