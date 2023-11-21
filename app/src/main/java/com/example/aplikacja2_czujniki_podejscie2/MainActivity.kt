package com.example.aplikacja2_czujniki_podejscie2

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.aplikacja2_czujniki_podejscie2.ui.theme.Aplikacja2CzujnikiPodejscie2Theme
import com.example.aplikacja2_czujniki_podejscie2.ui.theme.Pink80
import com.example.aplikacja2_czujniki_podejscie2.ui.theme.PurpleGrey80

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                   Aplikacja2CzujnikiPodejscie2Theme {
                       navController = rememberNavController()
                       SetupNavGraph(navController = navController)
                   }
                }
            }
        }

@Composable
fun ProximitySensor(
    navController: NavController
) {
    val ctx = LocalContext.current
    val sensorManager: SensorManager = ctx.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val proximitySensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
    val sensorStatus = remember {
        mutableStateOf("")
    }
    val proximitySensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        }

        override fun onSensorChanged(event: SensorEvent) {
            if (event.sensor.type == Sensor.TYPE_PROXIMITY){
                val distance = event.values[0]
                if (distance in 0.0..2.0){
                    sensorStatus.value = "bliziutko"
                } else if (distance in 2.0..4.0){
                    sensorStatus.value = "niedaleko"
                } else if (distance in 4.0..6.0){
                    sensorStatus.value = "no już trochę"
                } else if (distance in 6.0..8.0){
                    sensorStatus.value = "daleko"
                } else {
                    sensorStatus.value = "doliny stąd"
                }
            }
        }
    }
    sensorManager.registerListener(
        proximitySensorEventListener,
        proximitySensor,
        SensorManager.SENSOR_DELAY_NORMAL
    )
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(PurpleGrey80)
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Jesteś",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            fontSize = 30.sp, modifier = Modifier.padding(5.dp)
        )
        Text(
            text = sensorStatus.value,
            color = Color.Blue,
            fontWeight = FontWeight.W100,
            fontFamily = FontFamily.Default,
            fontSize = 40.sp, modifier = Modifier.padding(5.dp)
        )
        Button(onClick = {
            navController.navigate(route = Screen.Temperature.route)
        }) {
            Text(text = "Przejdż do czujnika temperatury")
        }
    }
}

@Composable
fun Czujniktemperatury(
    navController: NavController
) {
    val ctx = LocalContext.current
    val sensorManager: SensorManager = ctx.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val czujniktemperatury : Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
    val sensorStatus = remember {
        mutableStateOf("")
    }
    val czujniktemperaturyEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        }

        override fun onSensorChanged(event: SensorEvent) {
            if (event.sensor.type == Sensor.TYPE_AMBIENT_TEMPERATURE){
                val distance = event.values[0]
                if (distance in -300.0..-200.0){
                    sensorStatus.value = "mróz taki, że nie żyjesz"
                } else if (distance in -200.0..-100.0){
                    sensorStatus.value = "ziąb, lepiej uciekaj"
                } else if (distance in -100.0..-20.0){
                    sensorStatus.value = "znowu zamarłeś?"
                } else if (distance in -20.0..0.0){
                    sensorStatus.value = "niby na minusie, ale żyjesz"
                } else if (distance in 0.0..10.0){
                    sensorStatus.value = "chłodnawo"
                } else if (distance in 10.0..20.0){
                    sensorStatus.value = "w miarę ciepło"
                } else if (distance in 20.0..40.0) {
                    sensorStatus.value = "Witamy w ciepłych krajach"
                } else if (distance in 40.0..100.0){
                    sensorStatus.value = "Masz poparzenia"
                } else {
                    sensorStatus.value = "nie żyjesz"
                }
            }
        }
    }
    sensorManager.registerListener(
        czujniktemperaturyEventListener,
        czujniktemperatury,
        SensorManager.SENSOR_DELAY_NORMAL
    )
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Pink80)
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = sensorStatus.value,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            fontSize = 30.sp, modifier = Modifier.padding(5.dp)
        )
        Button(onClick = {
            navController.navigate(route = Screen.Pressure.route)
        }) {
            Text(text = "Przejdż do czujnika cisnienia")
        }
    }
}

@Composable
fun Czujnikcisnienia() {
    val ctx = LocalContext.current
    val sensorManager: SensorManager = ctx.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val czujnikcisnienia : Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)
    val sensorStatus = remember {
        mutableStateOf("")
    }
    val czujnikcisnieniaSensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        }

        override fun onSensorChanged(event: SensorEvent) {
            if (event.sensor.type == Sensor.TYPE_PRESSURE){
                if (event.values[0] == 0f){
                    sensorStatus.value = "zabiło wszystkich"
                } else {
                    sensorStatus.value = "jest dużeeee"
                }
            }
        }
    }
    sensorManager.registerListener(
        czujnikcisnieniaSensorEventListener,
        czujnikcisnienia,
        SensorManager.SENSOR_DELAY_NORMAL
    )
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Pink80)
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "Obecnie ciśnienie",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            fontSize = 30.sp, modifier = Modifier.padding(5.dp)
        )
        Text(
            text = sensorStatus.value,
            color = Color.Green,
            fontWeight = FontWeight.W900,
            fontFamily = FontFamily.Monospace,
            fontSize = 30.sp, modifier = Modifier.padding(5.dp)
        )
    }
}

