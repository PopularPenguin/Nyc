package com.popularpenguin.nyc.api

import com.popularpenguin.nyc.util.Constants.SCHOOLS_PATH
import com.popularpenguin.nyc.util.Constants.SCORES_PATH
import com.popularpenguin.nyc.data.SatScores
import com.popularpenguin.nyc.data.School
import retrofit2.http.GET

interface ApiService {
    @GET(SCHOOLS_PATH)
    suspend fun getSchools(): List<School>

    @GET(SCORES_PATH)
    suspend fun getSatScores(): List<SatScores>
}