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

    @Query("SELECT * FROM school ORDER BY dbn")
    suspend fun getSchools(): List<School>

    @Query("SELECT * FROM satScores ORDER BY dbn")
    suspend fun getAllSatScores(): List<SatScores>

    @Query("SELECT * FROM satScores WHERE dbn LIKE :dbn")
    suspend fun getSatScores(dbn: String): SatScores

    @Query("SELECT * FROM school INNER JOIN satScores ON satScores.dbn = school.dbn ORDER BY dbn")
    suspend fun getMatchingSchools(): List<School>

    @Query("SELECT * FROM satScores INNER JOIN school ON satScores.dbn = school.dbn ORDER BY dbn")
    suspend fun getMatchingSatScores(): List<SatScores>
}