package com.example.interview.ui.bookRoomActivity.supportFragment

import android.app.Dialog
import android.app.TimePickerDialog

import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import javax.inject.Inject

// Fragment for time picker
class TimePickerFragment @Inject constructor(): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        return TimePickerDialog(
            activity,
            activity as TimePickerDialog.OnTimeSetListener,
            hour,
            minute,
            true
        )
    }
}