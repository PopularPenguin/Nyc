package com.popularpenguin.nyc

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.popularpenguin.nyc.data.SatScores
import com.popularpenguin.nyc.data.School
import com.popularpenguin.nyc.ui.SchoolViewModel
import com.popularpenguin.nyc.ui.theme.blue1
import com.popularpenguin.nyc.ui.theme.blue3

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                }
            )
        }
    ) { paddingValues ->
        MainContent(paddingValues)
    }
}

@Composable
fun MainContent(paddingValues: PaddingValues) {
    val viewModel: SchoolViewModel = hiltViewModel()
    val schools by viewModel.schoolsFlow.collectAsState()
    val scores by viewModel.scoresFlow.collectAsState()

    // the selected school's SAT scores
    var currentScores by remember { mutableStateOf(SatScores()) }
    val setCurrentScores: (School) -> Unit = { school ->
        currentScores = scores.first {
            it.dbn == school.dbn
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.linearGradient(listOf(blue1, blue3)))
            .padding(paddingValues)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            text = stringResource(id = R.string.school_list_label),
            fontWeight = FontWeight.Bold
        )
        OutlinedCard(
            modifier = Modifier.padding(8.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(8.dp)
            ) {
                items(schools) { school ->
                    SchoolRow(school, setCurrentScores)
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        ScoresCard(scores = currentScores)
    }
}

@Composable
fun SchoolRow(school: School, setCurrentScores: (School) -> Unit) {
    Text(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp)
            .clickable { setCurrentScores(school) },
        text = school.name,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun ScoresCard(scores: SatScores) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        text = stringResource(id = R.string.sat_scores_label),
        fontWeight = FontWeight.Bold
    )
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp)
    ) {
        if (scores.dbn.isEmpty()) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(id = R.string.no_data),
                fontWeight = FontWeight.Bold
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Text(text = stringResource(id = R.string.school_name) + ": ${scores.name}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = stringResource(id = R.string.math) + ": ${scores.mathAvg}")
                Text(text = stringResource(id = R.string.reading) + ": ${scores.readingAvg}")
                Text(text = stringResource(id = R.string.writing) + ": ${scores.writingAvg}")
            }
        }
    }
}