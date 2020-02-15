package com.example.interview.respository.remote

import com.example.interview.models.restAPIModel.Room
import retrofit2.Call
import retrofit2.http.GET

interface RoomService {

    @GET("/yuhong90/7ff8d4ebad6f759fcc10cc6abdda85cf/raw/463627e7d2c7ac31070ef409d29ed3439f7406f6/room-availability.json")
     fun getDataList(): Call<List<Room>>
}