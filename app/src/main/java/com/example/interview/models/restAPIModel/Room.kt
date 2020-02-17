package com.example.interview.models.restAPIModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Room(
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("capacity")
    @Expose
    var capacity: Int? = null,
    @SerializedName("level")
    @Expose
    var level: Int? = null,
    @SerializedName("availability")
    @Expose
    var availability: MutableMap<String, String>? = null
) : iRoom