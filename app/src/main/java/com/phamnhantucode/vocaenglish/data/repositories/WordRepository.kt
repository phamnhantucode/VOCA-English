package com.phamnhantucode.vocaenglish.data.repositories

import com.phamnhantucode.vocaenglish.data.remote.api.dto.WordDto
import com.phamnhantucode.vocaenglish.domain.models.Word
import com.phamnhantucode.vocaenglish.utils.Resource
import kotlinx.coroutines.flow.Flow

interface WordRepository {
    suspend fun findWord(word: String): Flow<Resource<List<Word>>>
}