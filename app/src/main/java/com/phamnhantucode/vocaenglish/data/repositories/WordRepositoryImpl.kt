package com.phamnhantucode.vocaenglish.data.repositories

import com.phamnhantucode.vocaenglish.data.remote.api.WordApi
import com.phamnhantucode.vocaenglish.data.remote.api.dto.WordDto
import com.phamnhantucode.vocaenglish.domain.mapper.WordMapper
import com.phamnhantucode.vocaenglish.domain.models.Word
import com.phamnhantucode.vocaenglish.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class WordRepositoryImpl @Inject constructor(
    private val wordApi: WordApi
) : WordRepository {
    override suspend fun findWord(word: String): Flow<Resource<List<Word>>> {
        return flow {
            emit(Resource.Loading())
            val result = wordApi.getWord(word)
            emit(Resource.Success(data = result.map {
                WordMapper.mapToDomain(it)
            }))
            Timber.d(result.toString())
        }.catch {
            Timber.e(it.cause)
            emit(Resource.Error(message = it.message.toString()))
        }
    }


}