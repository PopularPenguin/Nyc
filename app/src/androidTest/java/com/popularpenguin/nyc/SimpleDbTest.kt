package com.popularpenguin.nyc

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.popularpenguin.nyc.room.AppDao
import com.popularpenguin.nyc.room.AppDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class SimpleDbTest {
    private lateinit var appDao: AppDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        appDao = db.dao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndReadSchools() = runTest {
        appDao.insertSchools(*schools.toTypedArray())

        val schoolsFromDb = appDao.getSchools()

        assertEquals(schoolsFromDb, schools.sortedBy { it.dbn })
    }

    @Test
    @Throws(Exception::class)
    fun insertAndReadScores() = runTest {
        appDao.insertSatScores(*scores.toTypedArray())

        val scoresFromDb = appDao.getAllSatScores()

        assertEquals(scoresFromDb, scores.sortedBy { it.dbn })
    }

    @Test
    @Throws(Exception::class)
    fun assertOnlyMatchesAreRead() = runTest {
        appDao.insertSchools(*schools.toTypedArray())
        appDao.insertSatScores(*scores.toTypedArray())

        val matchingSchoolsFromDb = appDao.getMatchingSchools()
        val matchingScoresFromDb = appDao.getMatchingSatScores()

        assertEquals(matchingSchoolsFromDb.size, matchingScoresFromDb.size)
        for (i in matchingSchoolsFromDb.indices) {
            assertEquals(matchingSchoolsFromDb[i].dbn, matchingScoresFromDb[i].dbn)
        }
        assertNull(matchingSchoolsFromDb.find { it.dbn == nonMatchingSchool.dbn })
        assertNull(matchingScoresFromDb.find { it.dbn == nonMatchingScores.dbn })
    }

    @Test
    @Throws(Exception::class)
    fun assertGetSatScoresReturnsCorrect() = runTest {
        appDao.insertSatScores(*scores.toTypedArray())

        val scoresToTest1 = appDao.getSatScores(school1.dbn)
        val scoresToTest2 = appDao.getSatScores(school2.dbn)

        assertEquals(school1.dbn, scoresToTest1.dbn)
        assertEquals(school2.dbn, scoresToTest2.dbn)
    }
}