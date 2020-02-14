package com.example.interview.ui.bookRoomActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.interview.R
import com.example.interview.models.Availability
import com.example.interview.models.Room
import com.example.interview.utils.CheckAvai
import kotlinx.android.synthetic.main.room_item.view.*
import javax.inject.Inject


class RoomListAdapter @Inject constructor(private var chooseTime: String) : RecyclerView.Adapter<RoomHolder>() {
    var roomList: List<Room> = arrayListOf()
    lateinit var defaultRoomList: List<Room> // store list for reset after sort

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.room_item, parent, false)
        return RoomHolder(view)
    }

    override fun getItemCount(): Int {
        return roomList.size
    }

    override fun onBindViewHolder(holder: RoomHolder, position: Int) {
        val room = roomList[position]
        holder.bind(
            room.name,
            room.level,
            room.capacity,
            CheckAvai.check(chooseTime, room.availability as Availability)
        )
    }

    fun addAll(list: List<Room>) {
        roomList = list
        notifyDataSetChanged()

    }

    fun setTime(time: String) {
        chooseTime = time
    }
    fun getTime() = chooseTime

    fun resetSort() {
        roomList = defaultRoomList.toList()
        notifyDataSetChanged()
    }

}


class RoomHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // bind into view
    fun bind(name: String?, level: Int?, capacity: Int?, isAvail: Boolean) {
        val view = itemView
        view.name.text = name
        val levelString = "Level $level"
        view.level.text = levelString
        val capString = "$capacity Pax"
        view.cap.text = capString
        if (isAvail) {
            view.avai.text = "Available"
        } else {
            view.avai.text = "Not Available"
        }
    }
}