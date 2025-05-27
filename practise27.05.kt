package com.example.paginator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.paginator.ui.theme.PaginatorTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val character_name = listOf("Scorpion", "Noob Saibot", "Sub-Zero")
            val character_image = listOf(R.drawable.img, R.drawable.img_1, R.drawable.img_2)
            val character_description = listOf(" Воскрешённый ниндзя и дух мщения. После гибели от рук Саб-Зиро он вернулся из Преисподней, чтобы отомстить и восстановить честь своего клана Ширай Рю. Его фирменный приём – «Get over here!».",
            "Тень и смерть в одном лице. Ранее был оригинальным Саб-Зиро, убитым Скорпионом, но возрождён как тёмный воин Куан Чи",
                "Мастер криомантии и защитник клана Лин Куэй. После смерти брата Би-Хана (оригинального Саб-Зиро) mantle принял Куай Лян")

            val pagerState = rememberPagerState { character_name.size }

            val scope = rememberCoroutineScope()

            Scaffold {

                Column(modifier = Modifier.fillMaxSize().padding(it), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Досье", fontWeight = FontWeight.Bold, fontSize = 25.sp)

                    HorizontalPager(
                        state = pagerState
                    ) { page ->

                        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                bitmap = ImageBitmap.imageResource(character_image[page]),
                                contentDescription = null,
                                modifier = Modifier.size(300.dp)
                            )

                            Text(text = character_description[page], fontSize = 15.sp)

                        }

                    }



                    Row{
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

                        for (char in character_name) {
                            TextButton (
                                onClick = {
                                    scope.launch {
                                        pagerState.scrollToPage(character_name.indexOf(char))
                                    }
                                }
                            ) {
                                Text(
                                    text = "${character_name.indexOf(char) + 1}"
                                )
                            }
                        }

                        IconButton(
                            onClick = {

                                scope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                }

                            }
                        ) {
                            Icon(
                                Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = null
                            )

                        }
                    }


                }
            }

        }
    }
}

