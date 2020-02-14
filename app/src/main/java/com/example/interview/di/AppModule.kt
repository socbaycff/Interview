package com.example.interview.di

import com.example.interview.respository.remote.RoomService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule {

    @Provides
    fun provideBaseURL(): String = "https://gist.githubusercontent.com"

    @Provides
    fun provideRoomService(url: String): RoomService {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RoomService::class.java)
    }
}