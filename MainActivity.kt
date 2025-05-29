package com.example.practise2905

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageBitmapConfig
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practise2905.ui.theme.Practise2905Theme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val image_naming = listOf("Smoking kills...", "Super dog", "Gym rat", "Ferrari", "Bike")
            val image_list = listOf(
                R.drawable.smoking, R.drawable.dog, R.drawable.gym_rat, R.drawable.ferrari,
                R.drawable.bike
            )
            val selected = remember { mutableStateOf(false) }

            val scope = rememberCoroutineScope()
            val pagerState = rememberPagerState { image_naming.size }

            Scaffold {

                Column(
                    modifier = Modifier.fillMaxSize().padding(it),

                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    HorizontalPager(state = pagerState) { page ->

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                bitmap = ImageBitmap.imageResource(image_list[page]),
                                contentDescription = null,
                                modifier = Modifier.size(400.dp)
                            )
                            Text(
                                text = image_naming[page],
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp
                            )
                        }
                    }

                    Row {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                }
                            }
                        ) {
                            Icon(
                                Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                contentDescription = null
                            )
                        }


                        for (char in image_naming) {
                            TextButton(
                                onClick = {
                                    scope.launch {
                                        pagerState.scrollToPage(image_naming.indexOf(char))

                                    }
                                    selected.value = true
                                }
                            ) {
                                Box {
                                    Canvas(Modifier.size(12.dp).border(1.dp,Color.Black, shape = CircleShape)) {
                                        val index = image_naming.indexOf(char)

                                        drawCircle(
                                            color = if (pagerState.currentPage == index ) Color.Black else Color.White,
                                            center = center,
                                            radius = 5.dp.toPx(),

                                        )



                                    }

                                }

                            }
                        }

                        Row {
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        pagerState.animateScrollToPage(pagerState.currentPage + 1)

                                    }

                                }
                            ) {
                                Icon(
                                    Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                    contentDescription = null, modifier = Modifier.size(20.dp)
                                )
                            }
                        }

                    }
                }

            }

        }
    }

}