package com.popularpenguin.nyc.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class School(
    @PrimaryKey
    val dbn: String = "",
    @Json(name = "school_name") val name: String = ""
)

@Entity
@JsonClass(generateAdapter = true)
data class SatScores(
    @PrimaryKey
    val dbn: String = "",
    @Json(name = "school_name") val name: String = "",
    @Json(name = "sat_math_avg_score") val mathAvg: String = "",
    @Json(name = "sat_critical_reading_avg_score") val readingAvg: String = "",
    @Json(name = "sat_writing_avg_score") val writingAvg: String = ""
)