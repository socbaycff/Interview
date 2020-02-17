package com.example.interview.ui.bookRoomActivity

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.interview.models.restAPIModel.Room

@BindingAdapter("level")
fun TextView.bindLevel(room: Room) {
    val value = "Level ${room.level}"
    text = value
}

@BindingAdapter("capacity")
fun TextView.bindCapacity(room: Room) {
    val value = "${room.capacity} Pax"
    text =  value
}

@BindingAdapter("available")
fun TextView.bindAvai(isAvailable: Boolean) {
   if (isAvailable) {
       text = "Available"
   } else {
       text = "Not Available"
   }
}