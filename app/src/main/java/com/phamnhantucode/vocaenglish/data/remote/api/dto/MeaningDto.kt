package com.phamnhantucode.vocaenglish.data.remote.api.dto

data class MeaningDto(
    val antonyms: List<String>,
    val definitions: List<DefinitionDto>,
    val partOfSpeech: String,
    val synonyms: List<String>
)