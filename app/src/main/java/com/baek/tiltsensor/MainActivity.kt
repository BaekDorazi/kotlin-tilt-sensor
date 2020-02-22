package com.baek.tiltsensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity(), SensorEventListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
        }
    }
}
