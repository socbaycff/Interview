package com.example.interview.utils

import com.example.interview.models.Availability

class CheckAvai {
    companion object {
        // check is A room available at choosen time
        fun check(time: String, availability: Availability): Boolean {
            return when {
                time.higher("19:30") -> availability.get1930() == "1"
                time.higher("19:00") -> availability.get1900() == "1"
                time.higher("18:30") -> availability.get1830() == "1"
                time.higher("18:30") -> availability.get1830() == "1"
                time.higher("18:30") -> availability.get1830() == "1"
                time.higher("18:00") -> availability.get1800() == "1"
                time.higher("17:30") -> availability.get1730() == "1"
                time.higher("17:00") -> availability.get1700() == "1"
                time.higher("16:30") -> availability.get1630() == "1"
                time.higher("16:00") -> availability.get1600() == "1"
                time.higher("15:30") -> availability.get1500() == "1"
                time.higher("15:00") -> availability.get1500() == "1"
                time.higher("14:30") -> availability.get1430() == "1"
                time.higher("14:00") -> availability.get1400() == "1"
                time.higher("13:30") -> availability.get1330() == "1"
                time.higher("13:00") -> availability.get1300() == "1"
                time.higher("12:30") -> availability.get1230() == "1"
                time.higher("12:00") -> availability.get1200() == "1"
                time.higher("11:30") -> availability.get1130() == "1"
                time.higher("11:00") -> availability.get1100() == "1"
                time.higher("10:30") -> availability.get1030() == "1"
                time.higher("10:00") -> availability.get1000() == "1"
                time.higher("09:30") -> availability.get0930() == "1"
                time.higher("09:00") -> availability.get0900() == "1"
                time.higher("08:30") -> availability.get0830() == "1"
                time.higher("08:00") -> availability.get0800() == "1"
                else -> availability.get1930() == "1" // below 8:00 use last night to check
            }


        }

        // helper function to compare 2 string
        fun String.higher(time: String): Boolean {
            return this.compareTo(time) > 0
        }

    }
}