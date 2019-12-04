package com.berangkat.kerja.model

data class OriginData(
        val ka_id: String,
        val ka_line: String,
        val route: String,
        val sts_from: String,
        var sts: String,
        var good_name: String,
        val sts_to: String,
        val arr_time: String,
        val dep_time: String,
        val notstop: String,
        val status: Any?
)

data class OriginModel(
        val total: Int,
        val data: List<OriginData>
)