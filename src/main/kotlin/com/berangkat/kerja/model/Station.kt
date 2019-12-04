package com.berangkat.kerja.model

data class Arrival(
        val ka_id: String,
        val from: String,
        val to: String,
        val arrival_time: String,
        val departure_time: String
)

data class Station(
        val id: String,
        val name: String,
        val schedule: MutableList<Arrival>? = mutableListOf()
)