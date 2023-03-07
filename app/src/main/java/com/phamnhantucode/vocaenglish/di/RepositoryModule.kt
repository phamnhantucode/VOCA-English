package com.phamnhantucode.vocaenglish.di

import com.phamnhantucode.vocaenglish.data.repositories.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindQuoteRepository(
        quoteRepository: QuoteRepositoryImpl
    ): QuoteRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepository: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindWordRepository(
        wordRepository: WordRepositoryImpl
    ): WordRepository
}