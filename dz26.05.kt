package com.example.dz2605

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dz2605.ui.theme.DZ2605Theme
import kotlinx.coroutines.Delay
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            fun1()
        }

    }
}

@Composable
fun fun1() {
    val scope = rememberCoroutineScope()
    var loadingState = remember { mutableStateOf("Нажмите кнопку") }
    val job = remember { mutableStateOf<Job?>(null) }


    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = loadingState.value, fontSize = 15.sp, fontWeight = FontWeight.Bold)


            Button(onClick = {
                loadingState.value = "Загрузка..."
                job.value?.cancel()

                job.value = scope.launch {


                    delay(2000)
                    val random_num = Random.nextInt(1,100)
                    loadingState.value = "${random_num} данных успешно загружены"
                    delay(2000)
                    loadingState.value = "Нажмите кнопку"
                }


            }) {
                Text(text = "Загрузить данные")

            }
        }
    }
}
