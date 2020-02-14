package com.example.interview.ui.supportFragment

import android.app.Dialog
import android.app.TimePickerDialog

import android.icu.util.Calendar
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.DialogFragment


class TimePickerFragment: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        return TimePickerDialog(activity, activity as TimePickerDialog.OnTimeSetListener, hour,minute,true)
    }
}