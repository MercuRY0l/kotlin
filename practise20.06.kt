package com.example.practise2006

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.practise2006.ui.theme.Practise2006Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practise2006Theme {
                Scaffold { paddingValues ->
                    val name = remember { mutableStateOf("") }
                    val age = remember { mutableStateOf("") }
                    val sliderPos = remember { mutableStateOf(0f) }
                    val scrollState = rememberScrollState()
                    val counter = remember { mutableStateOf(0) }

                    val text = remember { mutableStateOf("") }

                    val answers = listOf("Да", "Нет", "Затрудняюсь ответить")


                    val staticQuestions = listOf(
                        "Есть ли у вас навыки тестирования приложений?" to 1,
                        "Готовы к командировкам?" to 1,
                        "Есть ли у вас опыт работы более 2-х лет?" to 2,
                        "Работали ли вы с Kotlin более года?" to 2
                    )

                    val staticSelectedOptions = remember {
                        List(staticQuestions.size) { mutableStateOf("") }
                    }


                    val quizData = mapOf(
                        "Что делает ключевое слово `data` в Kotlin?" to listOf(
                            "Позволяет классу наследовать другие классы",
                            "Автоматически генерирует toString(), equals() и copy()",
                            "Делает класс абстрактным"
                        ),
                        "Чем отличается `val` от `var` в Kotlin?" to listOf(
                            "val можно переопределить, а var — нет",
                            "val — это неизменяемая ссылка, var — изменяемая",
                            "val используется только внутри функций"
                        ),
                        "Что означает ключевое слово `suspend`?" to listOf(
                            "Функция выполняется мгновенно",
                            "Функция может быть приостановлена и продолжена позже (в корутине)",
                            "Функция используется только для UI"
                        ),
                        "Какой способ создаёт неизменяемый список?" to listOf(
                            "mutableListOf(...)",
                            "arrayListOf(...)",
                            "listOf(...)"
                        ),
                        "Как правильно создать singleton в Kotlin?" to listOf(
                            "val obj = Singleton()",
                            "class Singleton private constructor() {}",
                            "object Singleton {}"
                        )
                    )

                    val quizSelectedOptions = remember {
                        quizData.map { mutableStateOf("") }
                    }

                    Column(
                        modifier = Modifier
                            .padding(paddingValues)
                            .padding(24.dp)
                            .fillMaxSize()
                            .verticalScroll(scrollState),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "ФИО:", modifier = Modifier.width(80.dp))
                            TextField(
                                value = name.value,
                                onValueChange = { name.value = it },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(56.dp)
                            )
                        }


                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Возраст:", modifier = Modifier.width(80.dp))
                            TextField(
                                value = age.value,
                                onValueChange = { age.value = it },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(56.dp)
                            )
                        }


                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Уровень ЗП:", modifier = Modifier.width(100.dp))
                            Text(
                                text = "${sliderPos.value.toInt()} $",
                                modifier = Modifier.padding(end = 12.dp)
                            )
                            Slider(
                                value = sliderPos.value,
                                valueRange = 800f..1600f,
                                onValueChange = { sliderPos.value = it },
                                modifier = Modifier.weight(1f)
                            )
                        }


                        staticQuestions.forEachIndexed { index, (question, score) ->
                            QuestionBlock(
                                question = question,
                                answers = answers,
                                selectedOption = staticSelectedOptions[index],
                                onScore = { answer ->
                                    if (answer == "Да") {
                                        counter.value += score
                                    }
                                }
                            )
                        }


                        quizData.entries.forEachIndexed { index, entry ->
                            QuestionBlock(
                                question = entry.key,
                                answers = entry.value,
                                selectedOption = quizSelectedOptions[index],
                                onScore = {}
                            )
                        }
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            if (name.value.isNotEmpty() && age.value.isNotEmpty()) {


                                Button(onClick = {
                                    val score = counter.value

                                    if (score >= 10) {
                                        text.value = "Тест пройден! Баллы: $score"
                                    } else {
                                        text.value = "Тест провален. Баллы: $score"
                                    }
                                }) {
                                    Text(text = "Завершить")
                                }

                                Spacer(modifier = Modifier.size(10.dp))

                                if (text.value.isNotEmpty()) {
                                    Text(text = text.value)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QuestionBlock(
    question: String,
    answers: List<String>,
    selectedOption: MutableState<String>,
    onScore: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black)
            .padding(8.dp)
    ) {
        Text(text = question)
        Spacer(modifier = Modifier.height(8.dp))

        Column(Modifier.selectableGroup()) {
            answers.forEach { answer ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .selectable(
                            selected = selectedOption.value == answer,
                            onClick = {
                                if (selectedOption.value != answer) {
                                    selectedOption.value = answer
                                    onScore(answer)
                                }
                            }
                        )
                ) {
                    RadioButton(
                        selected = (answer == selectedOption.value),
                        onClick = null
                    )
                    Text(text = answer)
                }
            }
        }
    }
}
