package com.popularpenguin.nyc

import com.popularpenguin.nyc.data.SatScores
import com.popularpenguin.nyc.data.School

val school1 = School(dbn = "aaa", name = "NYC High")
val school2 = School(dbn = "bbb", name = "Albany High")
val nonMatchingSchool = School(dbn = "123", name = "n/a")

val score1 = SatScores(
    dbn = "aaa", name = "NYC HIGH", mathAvg = "400", readingAvg = "399", writingAvg = "401"
)
val score2 = SatScores(
    dbn = "bbb", name = "Albany High", mathAvg = "370", readingAvg = "380", writingAvg = "390"
)
val nonMatchingScores = SatScores(
    dbn = "fff", name = "non match", mathAvg = "111", readingAvg = "222", writingAvg = "333"
)

val schools = listOf(school1, school2, nonMatchingSchool)
val scores = listOf(score1, score2, nonMatchingScores)