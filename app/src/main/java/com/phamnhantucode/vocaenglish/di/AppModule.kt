package com.phamnhantucode.vocaenglish.di

import com.google.firebase.auth.FirebaseAuth
import com.phamnhantucode.vocaenglish.data.remote.api.QuoteApi
import com.phamnhantucode.vocaenglish.data.remote.api.WordApi
import com.phamnhantucode.vocaenglish.data.repositories.AuthRepository
import com.phamnhantucode.vocaenglish.data.repositories.AuthRepositoryImpl
import com.phamnhantucode.vocaenglish.ui.utils.Constants.QUOTE_API_URL
import com.phamnhantucode.vocaenglish.ui.utils.Constants.WORD_API_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideQuoteApi(): QuoteApi = Retrofit.Builder()
        .baseUrl(QUOTE_API_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(QuoteApi::class.java)

    @Provides
    @Singleton
    fun provideWordApi(): WordApi = Retrofit.Builder()
        .baseUrl(WORD_API_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(WordApi::class.java)
}