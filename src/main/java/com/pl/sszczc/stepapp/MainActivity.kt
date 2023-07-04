package com.pl.sszczc.stepapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
//import android.hardware.Sensor
//import android.hardware.SensorEvent
//import android.hardware.SensorEventListener
//import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
//import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

//import com.mikhaellopez.circularprogressbar.CircularProgressBar

// import kotlin.math.sqrt

class MainActivity : AppCompatActivity() { // SensorEventListener
   // private var magnitudePreviousStep = 0.0
    private lateinit var navController: NavController
    private var previousTotalSteps = 0f
//    private var totalSteps = 0f
//    private var running: Boolean = false
//    private lateinit var sensorManager: SensorManager
    private val recognitionRequestedCode: Int = 100






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        if (isPermissionGranted()){
            requestPermissions()
        }

        loadData()
        resetSteps()

       // sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager


        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        setupWithNavController(bottomNavigationView, navController)



    }



    private fun resetSteps() {


    }

    private fun loadData() {
        val sharedPreferences: SharedPreferences =getSharedPreferences("step",
            Context.MODE_PRIVATE)
        val savedNo: Float = sharedPreferences.getFloat("Currentstep", 0f)
        previousTotalSteps = savedNo
    }

//    override fun onSensorChanged(event: SensorEvent?) {
//        val stepsTaken = findViewById<TextView>(R.id.steps_current)
//        val circularBar = findViewById<CircularProgressBar>(R.id.progress_circular)
//
//
//        // czy telefon jest w ruchu?
//
////        // dla samsunga
////        if (event!!.sensor.type == Sensor.TYPE_ACCELEROMETER){
////            val xaccel: Float = event.values[0]
////            val yaccel: Float = event.values[1]
////            val zaccel: Float = event.values[2]
////            val magnitude: Double = sqrt((xaccel * xaccel + yaccel * yaccel + zaccel * zaccel).toDouble())
////
////            val magnitudeDelta: Double = magnitude - magnitudePreviousStep
////            magnitudePreviousStep = magnitude
////
////            if (magnitudeDelta > 6) {
////                totalSteps++
////            }
////            val step: Int = totalSteps.toInt()
////            stepsTaken.text = step.toString()
////
////            circularBar.apply {
////                setProgressWithAnimation(step.toFloat())
////            }
////        }
////
////        else {
//            // to rozwiązanie podobno nie działa dla akcelerometru - między innymi samsung, ale na moim działa ;)
//
//        if (running) {
//            totalSteps = event!!.values[0]
//            val currentSteps = totalSteps.toInt() - previousTotalSteps.toInt()
//            stepsTaken.text = currentSteps.toString()
//            circularBar.apply {
//                setProgressWithAnimation(currentSteps.toFloat())
//            }
//        }
//        //}
//    }
//
//    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
//
//    }
//
//    override fun onResume() {
//        super.onResume()
//
//        running = true
//
//        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
//
//        val countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) // dla większości androidów
//        val detectorSensor = sensorManager.getDefaultSensor((Sensor.TYPE_STEP_DETECTOR)) // niektóre androidy mają taki sensor
//        val accelerometer = sensorManager.getDefaultSensor((Sensor.TYPE_ACCELEROMETER))  // ten zwłaszcza dla samsungów
//
//        when {
//            countSensor != null -> {
//                sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI)
//            }
//            detectorSensor != null -> {
//                sensorManager.registerListener(this, detectorSensor, SensorManager.SENSOR_DELAY_UI)
//            }
//            accelerometer != null -> {
//                sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI)
//            }
//            else -> {
//                Toast.makeText(this, "Twoje urządzenie nie jest kompatybilne.", Toast.LENGTH_LONG).show()
//            }
//        }
//    }
//
//    override fun onPause() {
//        super.onPause()
//
//        running = false
//
//        sensorManager.unregisterListener(this)
//    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this,
        arrayOf(android.Manifest.permission.ACTIVITY_RECOGNITION),
            recognitionRequestedCode)
    }

    private fun isPermissionGranted(): Boolean {

        return ContextCompat.checkSelfPermission(this,
        android.Manifest.permission.ACTIVITY_RECOGNITION) != PackageManager.PERMISSION_GRANTED
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            recognitionRequestedCode -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                   Toast.makeText(this, "Permission Granted :) ", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}