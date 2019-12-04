package com.berangkat.kerja.model

import com.google.gson.annotations.SerializedName

data class OpenCageData(
        val documentation: String,
        val licenses: List<License>,
        val rate: Rate,
        val results: List<Result>,
        val status: Status,
        val stay_informed: StayInformed,
        val thanks: String,
        val timestamp: Timestamp,
        val total_results: Int
)

data class License(
        val name: String,
        val url: String
)

data class Rate(
        val limit: Int,
        val remaining: Int,
        val reset: Int
)

data class Result(
        val annotations: Annotations,
        val bounds: Bounds,
        val components: Components,
        val confidence: Int,
        val formatted: String,
        val geometry: Geometry
)

data class Status(
        val code: Int,
        val message: String
)

data class StayInformed(
        val blog: String,
        val twitter: String
)

data class Timestamp(
        val created_http: String,
        val created_unix: Int
)

data class Annotations(
        val DMS: DMS,
        val MGRS: String,
        val Maidenhead: String,
        val Mercator: Mercator,
        val OSM: OSM,
        val UN_M49: UNM49,
        val callingcode: Int,
        val currency: Currency,
        val flag: String,
        val geohash: String,
        val qibla: Double,
        val roadinfo: Roadinfo,
        val sun: Sun,
        val timezone: Timezone,
        val what3words: What3words,
        val wikidata: String
)

data class DMS(
        val lat: String,
        val lng: String
)

data class Mercator(
        val x: Double,
        val y: Double
)

data class OSM(
        val edit_url: String,
        val url: String
)

data class UNM49(
        val regions: Regions,
        val statistical_groupings: List<String>
)

data class Regions(
        val ASIA: String,
        val ID: String,
        val SOUTHEAST_ASIA: String,
        val WORLD: String
)

data class Currency(
        val alternate_symbols: List<Any>,
        val decimal_mark: String,
        val html_entity: String,
        val iso_code: String,
        val iso_numeric: String,
        val name: String,
        val smallest_denomination: Int,
        val subunit: String,
        val subunit_to_unit: Int,
        val symbol: String,
        val symbol_first: Int,
        val thousands_separator: String
)

data class Roadinfo(
        val drive_on: String,
        val road: String,
        val speed_in: String
)

data class Sun(
        val rise: Rise,
        val set: Set
)

data class Rise(
        val apparent: Int,
        val astronomical: Int,
        val civil: Int,
        val nautical: Int
)

data class Set(
        val apparent: Int,
        val astronomical: Int,
        val civil: Int,
        val nautical: Int
)

data class Timezone(
        val name: String,
        val now_in_dst: Int,
        val offset_sec: Int,
        val offset_string: String,
        val short_name: String
)

data class What3words(
        val words: String
)

data class Bounds(
        val northeast: Northeast,
        val southwest: Southwest
)

data class Northeast(
        val lat: Double,
        val lng: Double
)

data class Southwest(
        val lat: Double,
        val lng: Double
)

data class Components(
        @SerializedName("ISO_3166-1_alpha-2")
        val iso1: String,
        @SerializedName("ISO_3166-1_alpha-3")
        val iso2: String,
        val _type: String,
        val city: String,
        val city_district: String,
        val continent: String,
        val country: String,
        val country_code: String,
        val county: String,
        val postcode: String,
        val road: String,
        val state_district: String,
        val station: String
)

data class Geometry(
        val lat: Double,
        val lng: Double
)