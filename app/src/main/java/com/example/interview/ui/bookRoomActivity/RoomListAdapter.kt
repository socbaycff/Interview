package com.example.interview.ui.bookRoomActivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.interview.databinding.RoomListItemBinding
import com.example.interview.models.restAPIModel.Room
import com.example.interview.utils.TimeChooseUtils
import javax.inject.Inject


class RoomListAdapter @Inject constructor(var chooseTime: String) :
    ListAdapter<Room, RoomHolder>(RoomDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomHolder {
        return RoomHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RoomHolder, position: Int) {
        val room = getItem(position)
        holder.bind(room, chooseTime)
    }

}

class RoomHolder(
    val binding: RoomListItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): RoomHolder {
            val binding =
                RoomListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return RoomHolder(binding)
        }
    }

    // old way
//    fun bind(name: String?, level: Int?, capacity: Int?, isAvail: Boolean) {
//        val view = itemView
//        view.name.text = name
//        val levelString = "Level $level"
//        view.level.text = levelString
//        val capString = "$capacity Pax"
//        view.cap.text = capString
//        if (isAvail) {
//            view.avai.text = "Available"
//        } else {
//            view.avai.text = "Not Available"
//        }
//    }

    // new binding way
    fun bind(room: Room, chooseTime: String) {
        binding.room = room
        binding.isAvailable =
            room.availability?.get(TimeChooseUtils.getCloseTime(chooseTime)) == "1"
        binding.executePendingBindings()
    }
}


class RoomDiffCallBack : DiffUtil.ItemCallback<Room>() {
    override fun areItemsTheSame(oldItem: Room, newItem: Room): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Room, newItem: Room): Boolean {
        return oldItem == newItem
    }

}