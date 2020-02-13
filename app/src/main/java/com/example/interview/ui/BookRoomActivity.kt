package com.example.interview.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.text.DateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.interview.R
import com.example.interview.models.Room
import com.example.interview.respository.remote.APIUtils
import com.example.interview.ui.supportFragment.DatePickerFragment
import com.example.interview.ui.supportFragment.SortSheetFragment
import com.example.interview.ui.supportFragment.SortType
import com.example.interview.ui.supportFragment.TimePickerFragment
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BookRoomActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, SortSheetFragment.ButtonClickListener {



    var roomAdapter = RoomListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setup()
        val roomService = APIUtils.getRoomService()
        val room = roomService.getRoom()

        room.enqueue(object : Callback<List<Room>?> {
            override fun onFailure(call: Call<List<Room>?>, t: Throwable) {
                Toast.makeText(applicationContext,"Fail",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Room>?>, response: Response<List<Room>?>) {
                val list = response.body()
                list?.let {
                    roomAdapter.addAll(it)
                }

            }
        })

        roomList.adapter = roomAdapter
        roomList.layoutManager = LinearLayoutManager(this)
    }

    fun setup() {
        DateEditText.editText!!.setOnClickListener {
            val datePicker = DatePickerFragment()
            datePicker.show(supportFragmentManager, "date picker")
        }

        TimeEditText.editText!!.setOnClickListener {
            val datePicker = TimePickerFragment()
            datePicker.show(supportFragmentManager, "time picker")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.camera -> {
                Toast.makeText(applicationContext,"Camera",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@BookRoomActivity,ScannedBarcodeActivity::class.java))
                false
            }
            else -> false
        }

    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val c = Calendar.getInstance()
        c.set(Calendar.YEAR, year)
        c.set(Calendar.MONTH, month)
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        val currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime())
        DateEditText.editText!!.setText(currentDateString)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        TimeEditText.editText!!.setText("$hourOfDay:$minute")
    }

    fun sorting(v: View) {
        val sortSheetFragment = SortSheetFragment()
        sortSheetFragment.show(supportFragmentManager,"sorting")
    }

    override fun onChooseSortType(type: SortType) {
            when (type) {
                SortType.AVAILABILITY -> Toast.makeText(applicationContext,"Avai",Toast.LENGTH_SHORT).show()
                SortType.LOCATION -> Toast.makeText(applicationContext,"Location",Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(applicationContext,"Capa",Toast.LENGTH_SHORT).show()
            }
    }

}
