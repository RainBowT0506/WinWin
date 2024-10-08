package com.rainbowt0506.winwin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rainbowt0506.winwin.repository.PatternRepository
import com.rainbowt0506.winwin.ui.theme.WinWinTheme
import com.rainbowt0506.winwin.viewmodel.PatternViewModel

class MainActivity : ComponentActivity() {

    lateinit var viewModel: PatternViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewModel = PatternViewModel(PatternRepository())
        viewModel.loadPatterns()
        setContent {
            setContent {
                MyApp(viewModel)
            }
        }
    }
}

@Composable
fun MyApp(viewModel: PatternViewModel) {
    val patterns by viewModel.patterns.observeAsState(emptyList())
    var selectedColor by remember { mutableStateOf<Long?>(null) }

    Column(modifier = Modifier.padding(bottom = 50.dp)) {
        // Top section displaying the selected color
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color(selectedColor?.toInt() ?: Color.Gray.toArgb()))
        )

        // Bottom section with selectable colors
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.White)
                .padding(start = 20.dp, top = 20.dp, end = 20.dp)
        ) {
            items(patterns.size) { hueIndex ->
                val hueGroup = patterns[hueIndex]

                Text(text = hueGroup.hue)
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    items(hueGroup.patterns.size) { index ->
                        val pattern = hueGroup.patterns[index]
                        val backgroundColor = pattern.color.toInt()

                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .padding(8.dp)
                                .background(Color(backgroundColor), shape = CircleShape)
                                .clickable {
                                    selectedColor = pattern.color
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = pattern.level.toString())
                        }
                    }
                }
            }
        }
    }
}