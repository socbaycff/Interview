package com.example.interview.respository.remote

import javax.inject.Inject

class RestAPIUtil @Inject constructor(var roomService: RoomService): iRestAPIUtil {


    override fun getRestServices(): RoomService {
        return roomService
    }
}