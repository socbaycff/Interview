@file:Suppress("UNUSED_PARAMETER")

package com.example.interview.ui.bookRoomActivity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.interview.R
import com.example.interview.databinding.ActivityBookroomBinding
import com.example.interview.di.ViewModelProviderFactory
import com.example.interview.ui.bookRoomActivity.supportFragment.DatePickerFragment
import com.example.interview.ui.bookRoomActivity.supportFragment.SortSheetFragment
import com.example.interview.ui.bookRoomActivity.supportFragment.SortType
import com.example.interview.ui.bookRoomActivity.supportFragment.TimePickerFragment
import com.example.interview.ui.scanQRActivity.ScanQRActivity
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

    private lateinit var binding: ActivityBookroomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bookroom)
        setSupportActionBar(binding.toolbar)
        viewModel = ViewModelProvider(this, providerFactory).get(BookRoomViewModel::class.java)
        viewModel.setup()
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this


        roomList.adapter = roomListAdapter
        roomList.layoutManager = LinearLayoutManager(this)

        // observe timeText change
        viewModel.timeText.observe(this, Observer {
            // update adapter chooseTime
            roomListAdapter.chooseTime = it
        })

    }


    fun chooseDate(v: View) {
        if (!datePickerFragment.isAdded) {
            datePickerFragment.show(supportFragmentManager, "date picker")
        }
    }

    fun chooseTime(v: View) {
        if (!timePickerFragment.isAdded) {
            timePickerFragment.show(supportFragmentManager, "time picker")
        }
    }

    fun openSortSheet(@Suppress("UNUSED_PARAMETER") v: View) {
        if (!sortSheetFragment.isAdded) {
            sortSheetFragment.show(supportFragmentManager, "openSortSheet")
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
                startActivity(Intent(this@BookRoomActivity, ScanQRActivity::class.java))
                false
            }
            else -> false
        }
    }


    // event handler for date picker
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        viewModel.setDate(year, month, dayOfMonth)
    }

    // event handler for time picker
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {

        viewModel.setTime(hourOfDay, minute)

        // only observe first time when adapter list is empty
        if (roomListAdapter.currentList.isEmpty()) {
            viewModel.roomList.observe(this, Observer {
                roomListAdapter.submitList(it)
            })

        } else {
            roomListAdapter.notifyDataSetChanged()
        }

    }

    // event handler after choose sort type
    override fun onChooseSortType(type: SortType) {

        when (type) {
            SortType.AVAILABILITY -> {

                viewModel.sortByAvailability()
                Toast.makeText(applicationContext, "sorted by avail ", Toast.LENGTH_SHORT).show()
            }
            SortType.LOCATION -> {
                //nothing
                Toast.makeText(applicationContext, "sorted by location: not yet ", Toast.LENGTH_SHORT).show()
            }
            else -> {
                viewModel.sortByCapacity()
                Toast.makeText(applicationContext, "sorted by capacity ", Toast.LENGTH_SHORT).show()
            }
        }

    }

    // event handler choose reset sort
    override fun onReset() {
        viewModel.resetSort()
        Toast.makeText(applicationContext, "Reset sort", Toast.LENGTH_SHORT).show()
    }

}
