package com.example.rickandmorty.appmodule

import com.example.rickandmorty.database.remote.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Provides
    @Singleton
    fun provideCreateNetworkRetrofit(): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://rickandmortyapi.com/api/")
            .build()

    @Provides
    @Singleton
    fun provideCryptoService(retrofit: Retrofit): RetrofitService =
        retrofit.create(RetrofitService::class.java)
}

