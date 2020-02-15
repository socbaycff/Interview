package com.example.interview.ui.bookRoomActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.interview.R
import com.example.interview.models.restAPIModel.Room
import com.example.interview.utils.TimeUtils
import kotlinx.android.synthetic.main.room_list_item.view.*
import javax.inject.Inject


class RoomListAdapter @Inject constructor(private var chooseTime: String) : RecyclerView.Adapter<RoomHolder>() {
    var roomList: List<Room> = arrayListOf()
    lateinit var defaultRoomList: List<Room> // store list for reset after sort

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomHolder {
     //   val binding = RoomItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.room_list_item, parent, false)
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
            room.availability?.get(TimeUtils.getCloseTime(chooseTime)) == "1"
        )
    }

    private fun addAll(list: List<Room>) {
        roomList = list
        notifyDataSetChanged()

    }

    private fun setTime(time: String) {
        chooseTime = time
    }
    fun getTime() = chooseTime

    fun resetSort() {
        roomList = defaultRoomList.toList()
        notifyDataSetChanged()
    }

    fun onTimeChooseSet(rooms: List<Room>, time: String) {
        if (roomList.isEmpty()) {
            addAll(rooms)
            defaultRoomList = roomList.toList()
            setTime(time)
        } else {
            setTime(time)
            notifyDataSetChanged()
        }
    }

    fun sortByCapacity() {
        val list = roomList.sortedBy { it.capacity }.asReversed()
        roomList = list
        notifyDataSetChanged()
    }

    fun sortByAvailability() {
        val chooseTime = TimeUtils.getCloseTime(getTime())
         val list = roomList.sortedBy {
            it.availability?.get(chooseTime) != "1"

        }

        roomList = list
        notifyDataSetChanged()
    }

}


class RoomHolder(view: View) : RecyclerView.ViewHolder(view) {

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