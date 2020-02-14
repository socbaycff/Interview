package com.example.interview.respository.remote

import javax.inject.Inject

class RestAPIUtil @Inject constructor() {

    @Inject
     lateinit var roomService: RoomService

    fun getRestRoomService(): RoomService {
        return roomService
    }
}