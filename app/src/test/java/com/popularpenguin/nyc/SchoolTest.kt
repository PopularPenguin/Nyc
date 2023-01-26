package com.popularpenguin.nyc

import com.popularpenguin.nyc.data.SatScores
import com.popularpenguin.nyc.data.School
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class SchoolTest {
    val california = School(name = "California High")
    val oregon = School(name = "Oregon High")
    val washington = School(name = "Washington High")

    val californiaSat = SatScores(
        name = "California High",
        mathAvg = "399",
        readingAvg = "375",
        writingAvg = "400"
    )
    val oregonSat = SatScores(
        name = "Washington High",
        mathAvg = "420",
        readingAvg = "401",
        writingAvg = "398"
    )
    val washingtonSat = SatScores(
        name = "Oregon High",
        mathAvg = "399",
        readingAvg = "400",
        writingAvg = "377"
    )

    lateinit var schools: List<School>
    lateinit var satScores: List<SatScores>

    @Before
    fun setup() {
        schools = listOf(california, oregon)
        satScores = listOf(californiaSat, washingtonSat)
    }

    @Test
    fun `can check specific school's sat scores`() {
        val schoolName = schools.filter { school ->
            school.name == "California High"
        }.map { school ->
            school.name
        }.first()

        val satScores = satScores.filter { scores ->
            scores.name == schoolName
        }.first()

        assertEquals(schoolName, satScores.name)
        assertEquals(satScores.mathAvg, californiaSat.mathAvg)
        assertEquals(satScores.readingAvg, californiaSat.readingAvg)
        assertEquals(satScores.writingAvg, californiaSat.writingAvg)
    }

    @Test
    fun `check school name and school sat scores not equals`() {
        val school = schools.first() // california
        val satScores = satScores.last() // washington

        assertNotEquals(school.name, satScores.name)
    }
}