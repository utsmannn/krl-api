package com.berangkat.kerja.model

data class OpenCageData(
        val results: List<Result>
)

data class Result(
        val geometry: Geometry
)

data class Geometry(
        val lat: Double,
        val lng: Double
)