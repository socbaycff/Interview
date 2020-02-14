package com.example.interview.utils

import com.example.interview.models.Availability

class CheckAvai {
    companion object {
        fun check(time: String, availability: Availability): Boolean {
           return when (time) {
               "8:00" -> availability.get0800() == "1"
            "8:30" -> availability.get0830() == "1"
            "9:00" -> availability.get0900() == "1"
            "9:30" -> availability.get0930() == "1"
            "10:00" ->  availability.get1000() == "1"
            "10:30" -> availability.get1030() == "1"
            "11:00" -> availability.get1100() == "1"
            "11:30" -> availability.get1130() == "1"
            "12:00" -> availability.get1200() == "1"
            "12:30" -> availability.get1230() == "1"
            "13:00" -> availability.get1300() == "1"
            "13:30" -> availability.get1330() == "1"
            "14:00" -> availability.get1400() == "1"
            "14:30" -> availability.get1430() == "1"
            "15:00" -> availability.get1500() == "1"
            "15:30" -> availability.get1500() == "1"
            "16:00" -> availability.get1600() == "1"
            "16:30" -> availability.get1630() == "1"
            "17:00" -> availability.get1700() == "1"
            "17:30" -> availability.get1730() == "1"
            "18:00" -> availability.get1800() == "1"
            "18:30" -> availability.get1830() == "1"
            "19:00" -> availability.get1900() == "1"
            "19:30" -> availability.get1930() == "1"
            else ->  false
            }


        }

    }
}