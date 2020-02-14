package com.example.interview.ui.bookRoomActivity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.interview.models.Room
import com.example.interview.respository.remote.RestAPIUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class BookRoomViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var restAPIUtil: RestAPIUtil


    var listRoom = MutableLiveData<List<Room>>()

    fun setup() {
        restAPIUtil.getRestRoomService().getRoom().enqueue(object : Callback<List<Room>?> {
            override fun onFailure(call: Call<List<Room>?>, t: Throwable) {
                Log.i("fail", "Fail to load")
            }

            override fun onResponse(call: Call<List<Room>?>, response: Response<List<Room>?>) {
                listRoom.value = response.body()
            }
        })


    }


}