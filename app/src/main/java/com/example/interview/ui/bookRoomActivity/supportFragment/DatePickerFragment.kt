package com.example.interview.ui.bookRoomActivity.supportFragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import javax.inject.Inject

// Fragment for date picker
class DatePickerFragment @Inject constructor(): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(
            activity as Context,
            activity as DatePickerDialog.OnDateSetListener,
            year,
            month,
            day
        )
    }
}