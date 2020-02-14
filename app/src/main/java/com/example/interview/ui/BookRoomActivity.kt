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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.interview.R
import com.example.interview.di.ViewModelProviderFactory
import com.example.interview.models.Availability
import com.example.interview.models.Room
import com.example.interview.ui.supportFragment.DatePickerFragment
import com.example.interview.ui.supportFragment.SortSheetFragment
import com.example.interview.ui.supportFragment.SortType
import com.example.interview.ui.supportFragment.TimePickerFragment
import com.example.interview.utils.CheckAvai
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_bookroom.*
import javax.inject.Inject


class BookRoomActivity : DaggerAppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, SortSheetFragment.ButtonClickListener {


    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    lateinit var viewModel: BookRoomViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookroom)
        setSupportActionBar(toolbar)
        setup()
        viewModel = ViewModelProvider(this, providerFactory).get(BookRoomViewModel::class.java)
        viewModel.setup()


        roomList.adapter = RoomListAdapter("0")
        roomList.layoutManager = LinearLayoutManager(this)
    }

    private fun setup() {
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
        val time = "$hourOfDay:$minute"
        TimeEditText.editText!!.setText(time)

        checkAvailability(time)
    }

    fun checkAvailability(time: String) {
        viewModel.listRoom.observe(this, Observer {

            val adapter = roomList.adapter as RoomListAdapter
            if (adapter.roomList.isEmpty()) {
                adapter.addAll(it)
                adapter.defaultRoomList = it.toList()
            }
            adapter.setTime(time)
        })

    }



    fun sorting(v: View) {
        val sortSheetFragment = SortSheetFragment()
        sortSheetFragment.show(supportFragmentManager,"sorting")
    }

    override fun onChooseSortType(type: SortType) {
        val adapter = roomList.adapter as RoomListAdapter
        var list: List<Room>? = null
            when (type) {
                SortType.AVAILABILITY -> {
                    //

                    val chooseTime = adapter.chooseTime
                    list = adapter.roomList.sortedBy {
                        !CheckAvai.check(
                            chooseTime,
                            it.availability as Availability
                        )
                    }


                    Toast.makeText(applicationContext,"sorted by avail ",Toast.LENGTH_SHORT).show()
                }
                SortType.LOCATION -> {
                        //nothing
                }
                else -> {
                    list = adapter.roomList.sortedBy { it.capacity }.asReversed()

                    Toast.makeText(applicationContext,"sorted by capacity ",Toast.LENGTH_SHORT).show()
                }
            }
             adapter.roomList = list as List<Room>
             adapter.notifyDataSetChanged()
    }

    override fun onReset() {
        (roomList.adapter as RoomListAdapter).resetFilter()
        Toast.makeText(applicationContext,"Reseted",Toast.LENGTH_SHORT).show()
    }

}
