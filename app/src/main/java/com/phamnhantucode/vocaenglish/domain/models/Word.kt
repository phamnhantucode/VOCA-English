package com.phamnhantucode.vocaenglish.domain.models

import com.phamnhantucode.vocaenglish.data.remote.api.dto.MeaningDto
import com.phamnhantucode.vocaenglish.data.remote.api.dto.PhoneticDto

data class Word(
    val word: String? = null,
    val phonetics: List<Phonetic>? = null,
    val meanings: List<Meaning>? = null
)