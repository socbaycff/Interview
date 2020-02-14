package com.example.interview.ui.bookRoomActivity

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
import com.example.interview.models.Room
import com.example.interview.ui.bookRoomActivity.supportFragment.DatePickerFragment
import com.example.interview.ui.bookRoomActivity.supportFragment.SortSheetFragment
import com.example.interview.ui.bookRoomActivity.supportFragment.SortType
import com.example.interview.ui.bookRoomActivity.supportFragment.TimePickerFragment
import com.example.interview.ui.scanQRActivity.ScannedBarcodeActivity
import com.example.interview.utils.CheckAvail
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_bookroom.*
import javax.inject.Inject


class BookRoomActivity : DaggerAppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener, SortSheetFragment.ButtonClickListener {


    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    private lateinit var viewModel: BookRoomViewModel

    @Inject
    lateinit var datePickerFragment: DatePickerFragment

    @Inject
    lateinit var timePickerFragment: TimePickerFragment

    @Inject
    lateinit var sortSheetFragment: SortSheetFragment

    @Inject
    lateinit var roomListAdapter: RoomListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookroom)
        setSupportActionBar(toolbar)
        setup()
        viewModel = ViewModelProvider(this, providerFactory).get(BookRoomViewModel::class.java)
        viewModel.setup()

        roomList.adapter = roomListAdapter
        roomList.layoutManager = LinearLayoutManager(this)


    }

    private fun setup() {
        DateEditText.editText!!.setOnClickListener {
            datePickerFragment.show(supportFragmentManager, "date picker")
        }

        TimeEditText.editText!!.setOnClickListener {
            timePickerFragment.show(supportFragmentManager, "time picker")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.camera -> {
                Toast.makeText(applicationContext, "Camera", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@BookRoomActivity, ScannedBarcodeActivity::class.java))
                false
            }
            else -> false
        }
    }


    // event handler for date picker
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val c = Calendar.getInstance()
        c.set(Calendar.YEAR, year)
        c.set(Calendar.MONTH, month)
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        val currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.time)
        DateEditText.editText!!.setText(currentDateString)
    }

    // event handler for time picker
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        // add "0" to time if necessary
        val minuteString: String = if (minute < 10) "0$minute" else "$minute"
        val hourString: String = if (hourOfDay < 10) "0$hourOfDay" else "$hourOfDay"

        val time = "$hourString:$minuteString"

        TimeEditText.editText!!.setText(time)
        loadRoomListWithTime(time)
    }

    private fun loadRoomListWithTime(time: String) {
        viewModel.listRoom.observe(this, Observer {

            val adapter = roomList.adapter as RoomListAdapter
            if (adapter.roomList.isEmpty()) {
                adapter.addAll(it)
                adapter.defaultRoomList = it.toList() // default list for reset
                adapter.setTime(time)
            } else {
                adapter.setTime(time)// set tim for availability process
                adapter.notifyDataSetChanged()
            }

        })

    }


    fun openSortSheet(@Suppress("UNUSED_PARAMETER") v: View) {
        sortSheetFragment.show(supportFragmentManager, "openSortSheet")
    }

    // event handler after choose sort type
    override fun onChooseSortType(type: SortType) {
        val adapter = roomList.adapter as RoomListAdapter
        var list: List<Room>? = null

        when (type) {
            SortType.AVAILABILITY -> {

                val chooseTime = CheckAvail.getCloseTime(adapter.getTime())
                list = adapter.roomList.sortedBy {
                    it.availability?.get(chooseTime) != "1"

                }
                Toast.makeText(applicationContext, "sorted by avail ", Toast.LENGTH_SHORT).show()
            }
            SortType.LOCATION -> {
                //nothing
            }
            else -> {
                list = adapter.roomList.sortedBy { it.capacity }.asReversed()
                Toast.makeText(applicationContext, "sorted by capacity ", Toast.LENGTH_SHORT).show()
            }
        }

        adapter.roomList = list as List<Room>
        adapter.notifyDataSetChanged()
    }

    // event handler choose reset sort
    override fun onReset() {
        (roomList.adapter as RoomListAdapter).resetSort()
        Toast.makeText(applicationContext, "Reset", Toast.LENGTH_SHORT).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("Date", DateEditText.editText?.text.toString())
        outState.putString("Time", TimeEditText.editText?.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        DateEditText.editText?.setText(savedInstanceState.getString("Date"))
        TimeEditText.editText?.setText(savedInstanceState.getString("Time"))
    }

}
