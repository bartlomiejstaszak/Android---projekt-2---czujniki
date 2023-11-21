package com.example.aplikacja2_czujniki_podejscie2

sealed class Screen(val route: String){
    object Proximity: Screen(route = "proximity_screen")
    object Temperature: Screen(route = "temperature_screen")
    object Pressure: Screen(route = "pressure_screen")
}
