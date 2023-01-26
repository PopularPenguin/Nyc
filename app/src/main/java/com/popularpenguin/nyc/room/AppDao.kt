package com.popularpenguin.nyc.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.popularpenguin.nyc.data.SatScores
import com.popularpenguin.nyc.data.School

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchools(vararg schools: School)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSatScores(vararg scores: SatScores)

    @Query("SELECT * FROM school")
    suspend fun getSchools(): List<School>

    @Query("SELECT * FROM satScores")
    suspend fun getAllSatScores(): List<SatScores>

    @Query("SELECT * FROM satScores WHERE name LIKE :schoolName")
    suspend fun getSatScores(schoolName: String): SatScores

    @Query("SELECT * FROM school INNER JOIN satScores ON satScores.dbn = school.dbn")
    suspend fun getMatchingSchools(): List<School>

    @Query("SELECT * FROM satScores INNER JOIN school ON satScores.dbn = school.dbn")
    suspend fun getMatchingSatScores(): List<SatScores>
}