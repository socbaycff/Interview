package com.example.interview.respository.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "booking_room")
data class RoomEntity(@PrimaryKey var name: String,
                      @ColumnInfo(name = "capacity")
                       var capacity:Int,
                      @ColumnInfo(name = "level")
                       var level: Int)
