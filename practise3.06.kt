package com.example.group234

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()


            Scaffold {
                Column(modifier = Modifier.padding(it)) {
                    NavHost(
                        navController = navController, startDestination = "main"
                    ) {
                        composable(route = "main") {
                            Main()
                        }
                        composable(route = "message") {
                            Message()
                        }
                        composable(route = "groups") {
                            Groups()
                        }
                        composable(route = "menu") {
                            Menu()
                        }
                    }
                    Row {
                        IconButton(
                            onClick = {
                                navController.navigate(route = "main") {
                                    popUpTo(route = "main")
                                }
                            }
                        ) {
                            Icon(Icons.Filled.Home, contentDescription = "Home")
                        }
                        IconButton(
                            onClick = {
                                navController.navigate(route = "message")
                            }
                        ) {
                            Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Message")
                        }
                        IconButton(
                            onClick = {
                                navController.navigate(route = "groups")
                            }
                        ) {
                            Icon(Icons.Filled.Person, contentDescription = "Groups")
                        }

                        IconButton(
                            onClick = {
                                navController.navigate(route = "menu")
                            }
                        ) {
                            Icon(Icons.Filled.Menu, contentDescription = "menu")
                        }

                    }
                }


            }
        }
    }
}

@Composable
fun Main(){
    Text(text = "Главная")
}

@Composable
fun Message(){
    Text(text = "Сообщения")
}

@Composable
fun Groups(){
    Text(text = "Группы")
}

@Composable
fun Menu(){
    Text(text = "Меню")
}