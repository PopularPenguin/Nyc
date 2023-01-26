package com.popularpenguin.nyc.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.popularpenguin.nyc.api.ApiService
import com.popularpenguin.nyc.data.SatScores
import com.popularpenguin.nyc.data.School
import com.popularpenguin.nyc.room.AppDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolViewModel
@Inject
constructor(
    private val apiService: ApiService,
    private val dao: AppDao
) : ViewModel() {

    var schoolsFlow = MutableStateFlow(emptyList<School>())
        private set
    var scoresFlow = MutableStateFlow(emptyList<SatScores>())
        private set

    init {
        viewModelScope.launch {
            // populate db on start if it is empty
            if (dao.getSchools().isEmpty()) {
                val schools = apiService.getSchools()
                val scores = apiService.getSatScores()

                dao.insertSchools(*schools.toTypedArray())
                dao.insertSatScores(*scores.toTypedArray())
            }

            // emit schools where there is available SAT data
            schoolsFlow.emit(dao.getMatchingSchools())
            scoresFlow.emit(dao.getMatchingSatScores())
        }
    }
}