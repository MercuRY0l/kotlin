package com.example.group234

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practise306.R
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() { override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
            val navController = rememberNavController()

            val scope = rememberCoroutineScope()

            Scaffold(bottomBar = {BottomAppBar {

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    IconButton(
                        onClick = {
                            navController.navigate(route = "main") {
                                popUpTo(route = "main")
                            }



                        }
                    ) {
                        Icon(Icons.Filled.Home, contentDescription = "Home", modifier = Modifier.size(35.dp))
                    }
                    IconButton(
                        onClick = {
                            navController.navigate(route = "message")
                        }
                    ) {
                        Icon(Icons.Filled.Send, contentDescription = "Message", modifier = Modifier.size(35.dp))
                    }
                    IconButton(
                        onClick = {
                            navController.navigate(route = "groups")
                        }
                    ) {
                        Icon(Icons.Filled.Person, contentDescription = "Groups", modifier = Modifier.size(35.dp))
                    }

                    IconButton(
                        onClick = {
                            navController.navigate(route = "menu")
                        }
                    ) {
                        Icon(Icons.Filled.Menu, contentDescription = "menu", modifier = Modifier.size(35.dp))
                    }

                }



            }}) {



                Column(modifier = Modifier.padding(it).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    NavHost(
                        navController = navController, startDestination = "main"
                    ) {
                        composable(route = "main") {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Main()

                                Spacer(modifier = Modifier.size(14.dp))

                                Image(
                                    bitmap = ImageBitmap.imageResource(R.drawable.img1),
                                    contentDescription = null, modifier = Modifier.size(300.dp)
                                )
                            }
                        }
                        composable(route = "message") {

                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Message()

                                Spacer(modifier = Modifier.size(14.dp))

                                Image(
                                    bitmap = ImageBitmap.imageResource(R.drawable.img2),
                                    contentDescription = null, modifier = Modifier.size(300.dp)
                                )
                            }
                        }
                        composable(route = "groups") {

                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Groups()

                                Spacer(modifier = Modifier.size(14.dp))

                                Image(
                                    bitmap = ImageBitmap.imageResource(R.drawable.img3),
                                    contentDescription = null, modifier = Modifier.size(300.dp)
                                )
                            }
                        }
                        composable(route = "menu") {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                                Menu()

                                Spacer(modifier = Modifier.size(14.dp))

                                Image(
                                    bitmap = ImageBitmap.imageResource(R.drawable.img4),
                                    contentDescription = null, modifier = Modifier.size(300.dp)
                                )
                            }
                        }
                    }


                }

            }
        }
    }
}

@Composable
fun Main(){
    Text(text = "Главная", fontWeight = FontWeight.Bold, fontSize = 20.sp)

}

@Composable
fun Message(){
    Text(text = "Сообщения", fontWeight = FontWeight.Bold, fontSize = 20.sp)
}

@Composable
fun Groups(){
    Text(text = "Группы", fontWeight = FontWeight.Bold, fontSize = 20.sp)
}

@Composable
fun Menu(){
    Text(text = "Меню", fontWeight = FontWeight.Bold, fontSize = 20.sp)
}
