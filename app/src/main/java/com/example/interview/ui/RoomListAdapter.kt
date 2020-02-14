package com.example.interview.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.interview.R
import com.example.interview.models.Availability
import com.example.interview.models.Room
import com.example.interview.utils.CheckAvai
import kotlinx.android.synthetic.main.room_item.view.*

class RoomListAdapter(var chooseTime: String): RecyclerView.Adapter<RoomHolder>() {
    var roomList: List<Room> = arrayListOf()
    lateinit var defaultRoomList: List<Room>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.room_item, parent, false)
        return RoomHolder(view)
    }

    override fun getItemCount(): Int {
        return roomList.size
    }

    override fun onBindViewHolder(holder: RoomHolder, position: Int) {
        val room = roomList[position]
        holder.bind(room.name,room.level,room.capacity,CheckAvai.check(chooseTime, room.availability as Availability))
    }

    fun addAll(list: List<Room>) {
        roomList = list
        notifyDataSetChanged()

    }

    fun setTime(time: String) {
        chooseTime = time
        notifyDataSetChanged()
    }

    fun resetFilter() {
        roomList = defaultRoomList.toList()
        notifyDataSetChanged()
    }

}


class RoomHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(name: String?, level: Int?, capacity : Int?,isAvail: Boolean) {
        val view = itemView
        view.name.text = name
        view.level.text = "Level $level"
        view.cap.text = "$capacity Pax"
        if (isAvail) {
            view.avai.text = "Available"
        } else {
            view.avai.text = "Not Available"
        }
    }
}