package com.berangkat.kerja.model

import com.google.gson.annotations.SerializedName

data class OriginData(
        val ka_id: String,
        val ka_line: String,
        val route: String,
        @SerializedName("sts_from")
        val from: String,
        @SerializedName("sts")
        var station: String,
        var station_name: String,
        @SerializedName("sts_to")
        val to: String,
        val arr_time: String,
        val dep_time: String,
        val notstop: String,
        val status: Any?
)

data class OriginModel(
        val total: Int,
        val data: List<OriginData>
)