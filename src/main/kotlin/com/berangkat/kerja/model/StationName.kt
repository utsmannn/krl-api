package com.berangkat.kerja.model

import com.google.gson.annotations.SerializedName

data class StationName(
        @SerializedName("sts_id")
        val id: String,
        @SerializedName("sts_name")
        var name: String,
        var good_name: String,
        var geometry: List<Double?>? = null
)

data class StationResponses(
        val total: Int,
        val data: List<StationName>
)