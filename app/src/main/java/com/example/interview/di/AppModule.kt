package com.example.interview.di

import com.example.interview.respository.remote.RestAPIUtil
import com.example.interview.respository.remote.RoomService
import com.example.interview.respository.remote.iRestAPIUtil
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
abstract class AppModuleBinding {

    // bind into interface

    @Binds
    abstract fun bindIRestAPIUtil(restAPIUtil: RestAPIUtil): iRestAPIUtil

    companion object

}

@Module
class AppModuleProvider {
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

