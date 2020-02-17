package com.example.interview.respository.local

import androidx.room.Dao
import androidx.room.Query
import com.example.interview.models.restAPIModel.Room

@Dao
interface BookingRoomDAO {
    @Query("SELECT * FROM booking_room")
    fun getAll(): Room?
}