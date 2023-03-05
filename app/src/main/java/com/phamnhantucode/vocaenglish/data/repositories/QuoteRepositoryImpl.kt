package com.phamnhantucode.vocaenglish.data.repositories

import com.phamnhantucode.vocaenglish.data.remote.api.QuoteApi
import com.phamnhantucode.vocaenglish.data.remote.api.dto.QuoteDtoItem
import com.phamnhantucode.vocaenglish.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    val api: QuoteApi
): QuoteRepository {
    override suspend fun getRandomQuote(): Flow<Resource<QuoteDtoItem>> {
        return flow {
            emit(Resource.Loading())
            val result = api.getRandomQuote()
            emit(Resource.Success(data = result.first()))
        }.catch {
            emit(Resource.Error(message = it.message.toString()))
        }
    }
}