package com.example.interview.ui.bookRoomActivity

import android.icu.text.DateFormat
import android.icu.util.Calendar
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.interview.models.restAPIModel.Room
import com.example.interview.respository.remote.iRestAPIUtil
import com.example.interview.utils.TimeChooseUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class BookRoomViewModel @Inject constructor() : ViewModel(),
    iBookRoomViewModel {

    @Inject
    lateinit var restAPIUtil: iRestAPIUtil

    var roomList = MutableLiveData<List<Room>>()
    lateinit var defaultListRoom: List<Room> // for reset after sort

    var dateText = MutableLiveData<String>()
    var timeText = MutableLiveData<String>()


    override fun setup() {
        restAPIUtil.getRestServices().getDataList()
        restAPIUtil.getRestServices().getDataList().enqueue(object : Callback<List<Room>> {
            override fun onFailure(call: Call<List<Room>?>, t: Throwable) {
                Log.i("fail", "Fail to load")
            }

            override fun onResponse(call: Call<List<Room>?>, response: Response<List<Room>?>) {
                val list = response.body()
                roomList.value = list
                defaultListRoom = list!!
            }
        })

        timeText.value = ""

    }


    fun resetSort() {
        roomList.value = defaultListRoom
    }


    fun sortByCapacity() {
        val list = roomList.value?.sortedBy { it.capacity }?.asReversed()
        roomList.value = list
    }

    fun sortByAvailability() {

        val list = roomList.value?.sortedBy {
            it.availability?.get(TimeChooseUtils.getCloseTime(timeText.value!!)) != "1"
        }

        roomList.value = list
    }

    fun setDate(year: Int, month: Int, dayOfMonth: Int) {
        val c = Calendar.getInstance()
        c.set(Calendar.YEAR, year)
        c.set(Calendar.MONTH, month)
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        val currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.time)
        dateText.value = currentDateString
    }

    fun setTime(hourOfDay: Int, minute: Int) {
        // add "0" to time if necessary
        val minuteString: String = if (minute < 10) "0$minute" else "$minute"
        val hourString: String = if (hourOfDay < 10) "0$hourOfDay" else "$hourOfDay"

        val time = "$hourString:$minuteString"

        timeText.value = time
    }
}