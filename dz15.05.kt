package com.example.dz1505

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dz1505.ui.theme.DZ1505Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {


            val scope = rememberCoroutineScope()
            val SnackBarHostState = remember { SnackbarHostState() }


            Scaffold(
                snackbarHost = {
                    SnackbarHost(hostState = SnackBarHostState) { data ->
                        Snackbar(
                            snackbarData = data,
                            actionOnNewLine = true,
                            shape = RoundedCornerShape(size = 10.dp),
                            containerColor = Color.DarkGray,
                            contentColor = Color.Yellow,
                            actionColor = Color.Yellow
                        )
                    }
                }
            ) {
                Column(
                    modifier = Modifier
                        .padding(it)
                ) {
                    Button(

                        onClick = {
                            scope.launch {
                                SnackBarHostState.showSnackbar("Кнопка была нажата!")
                            }
                        }
                    ) { Text("Кнопка 1") }

                    Button(
                        onClick = {
                            scope.launch {
                                var res = SnackBarHostState.showSnackbar(
                                    message = "Кнопка была нажата!",
                                    actionLabel = "Отмена")

                                if (res == SnackBarHostState.showSnackbar("Отмена")){
                                    SnackBarHostState.showSnackbar("Действие было отменено!")
                                }

                            }
                        }

                    ) {
                        Text(text = "Кнопка 2")
                    }

                    Button(onClick = {
                        scope.launch {
                            var res = SnackBarHostState.showSnackbar(
                                message = "Кнопка была нажата!",
                                withDismissAction = true,
                                duration = SnackbarDuration.Indefinite

                            )

                        }
                    }) {

                        Text(text = "Кнопка 3")
                    }

                }



            }
        }
    }
}