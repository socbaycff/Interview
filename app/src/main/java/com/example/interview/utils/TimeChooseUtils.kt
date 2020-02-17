package com.example.interview.utils

class TimeChooseUtils {
    companion object {
        val timeMarks = arrayListOf("19:30","19:00", "18:30","18:00","17:30","17:00","16:30","16:00","15:30","15:00","14:30","14:00","13:30","13:00","12:30","12:00","11:30","11:00","10:30","10:00",
            "09:30","09:00","08:30","08:00")
        // getClosetTime
        fun getCloseTime(time: String): String {
            for (timeMark in timeMarks) {
                if(time.higher(timeMark)) {
                    return timeMark
                }
            }

            return timeMarks[0]

        }

        // helper function to compare 2 string
        private fun String.higher(time: String): Boolean {
            return this > time
        }


    }
}