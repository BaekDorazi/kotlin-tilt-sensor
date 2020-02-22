package com.baek.tiltsensor

import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var tiltView: TiltView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE //화면 가로로 고정
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON) //화면 안꺼지도록

        tiltView = TiltView(this)
        setContentView(tiltView)
    }

    private val sensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()

        sensorManager.registerListener(
            this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    /**
     * 센서값이 변경되면 호출
     * values[0] : x 축 -> 위로 기울이면 -10~0, 아래로 기울이면 0~10
     * values[1] : y 축 -> 왼쪽으로 기울이면 -10~0, 오른쪽으로 기울이면 0~10
     * values[2] : z 축 -> 미사용
     */
    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            Log.d(
                "Debug",
                "onSensorChanged: x : ${event.values[0]}, y : ${event.values[1]}, z: ${event.values[2]}"
            )

            tiltView.onSensorEvent(event)
        }
    }
}
