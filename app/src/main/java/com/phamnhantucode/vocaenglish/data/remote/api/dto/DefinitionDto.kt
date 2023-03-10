package com.phamnhantucode.vocaenglish.data.remote.api.dto

data class DefinitionDto(
    val antonyms: List<String>,
    val definition: String,
    val example: String,
    val synonyms: List<String>
)