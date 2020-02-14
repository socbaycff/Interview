package com.example.interview.respository.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "booking_room")
data class BookingRoom(@PrimaryKey var name: String,
                       @ColumnInfo(name = "capacity")
                       var capacity:Int,
                       @ColumnInfo(name = "level")
                       var level: Int,
                       @ColumnInfo(name = "availability")
                       var availability: MutableMap<String, String>)
