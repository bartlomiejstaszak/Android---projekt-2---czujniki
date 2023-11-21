package com.example.aplikacja2_czujniki_podejscie2

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Proximity.route
    ){
        composable(
            route = Screen.Proximity.route
        ){
            ProximitySensor(navController = navController)
        }
        composable(
            route = Screen.Temperature.route
        ){
            Czujniktemperatury(navController = navController)
        }
        composable(
            route = Screen.Pressure.route
        ){
            Czujnikcisnienia()
        }
    }
}