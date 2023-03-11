package com.phamnhantucode.vocaenglish.domain.mapper

import com.phamnhantucode.vocaenglish.data.remote.api.dto.WordDto
import com.phamnhantucode.vocaenglish.domain.models.Definition
import com.phamnhantucode.vocaenglish.domain.models.Meaning
import com.phamnhantucode.vocaenglish.domain.models.Phonetic
import com.phamnhantucode.vocaenglish.domain.models.Word

object WordMapper {
    fun mapToDomain(wordDto: WordDto): Word {
        return Word(
            word = wordDto.word,
            phonetics = wordDto.phonetics?.map {
                Phonetic(
                    text = it.text,
                    audio = it.audio
                )
            },
            meanings = wordDto.meanings?.map {
                Meaning(
                    antonyms = it.antonyms,
                    synonyms = it.synonyms,
                    partOfSpeech = it.partOfSpeech,
                    definitions = it.definitions.map {
                        Definition(
                            antonyms = it.antonyms,
                            synonyms = it.synonyms,
                            definition = it.definition,
                            example = it.example
                        )
                    }
                )
            }
        )
    }
}