package com.app.finance.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.nursyah.finance.navigation.NavGraph
import com.nursyah.finance.navigation.SCREEN_HOME
import com.nursyah.finance.presentation.components.Navbar
import com.nursyah.finance.presentation.theme.FinanceTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            navController = rememberAnimatedNavController()
            FinanceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App(navController)
                }
            }
        }
    }
}

@Composable
fun App(navController: NavHostController) {
    val ctx = LocalContext.current
    var navbarSelected by rememberSaveable { mutableStateOf(SCREEN_HOME) }

    Scaffold(
        bottomBar = {
            Navbar(
                onClick = { navbarSelected = it },
                value = navbarSelected,
                navController = navController,
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = {

                },
                contentColor = Color.White,
                modifier = Modifier
                    .size(50.dp)
                    .offset(y = 50.dp)
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add icon")
            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            NavGraph(navController = navController)
        }
    }
}

