package com.example.compassv3

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(),SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private lateinit var img:ImageView
    private var currentDegree = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        img = findViewById(R.id.imgCompass)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION)
            ,SensorManager.SENSOR_DELAY_GAME)

    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)

    }
    override fun onSensorChanged(event: SensorEvent?) {
        val degree = Math.round(event!!.values[0]).toFloat()
        val rotate = RotateAnimation(currentDegree,(-degree),
            Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f)
        rotate.duration = 210
        rotate.fillAfter = true
        img.startAnimation(rotate)
        currentDegree = -degree
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}