package com.example.interview.utils

class CheckAvail {
    companion object {
        // getClosetTime
        fun getCloseTime(time: String): String {
            return when {
                time.higher("19:30") -> "19:30"
                time.higher("19:00") -> "19:00"
                time.higher("18:30") -> "18:30"
                time.higher("18:30") -> "18:30"
                time.higher("18:30") -> "18:30"
                time.higher("18:00") -> "18:00"
                time.higher("18:00") -> "18:00"
                time.higher("17:00") -> "17:00"
                time.higher("16:30") -> "16:30"
                time.higher("16:00") -> "16:00"
                time.higher("15:30") -> "15:30"
                time.higher("15:00") -> "15:00"
                time.higher("14:30") -> "14:30"
                time.higher("14:00") -> "14:00"
                time.higher("13:30") -> "13:30"
                time.higher("13:00") -> "13:00"
                time.higher("12:30") -> "12:30"
                time.higher("12:00") -> "12:00"
                time.higher("11:30") -> "11:30"
                time.higher("11:00") -> "11:00"
                time.higher("10:30") -> "10:30"
                time.higher("10:00") -> "10:00"
                time.higher("09:30") -> "09:30"
                time.higher("09:00") -> "09:00"
                time.higher("08:30") -> "08:30"
                time.higher("08:00") -> "08:00"
                else -> "19:30" // below 8:00 use last night to check
            }

        }

        // helper function to compare 2 string
        private fun String.higher(time: String): Boolean {
            return this > time
        }


    }
}