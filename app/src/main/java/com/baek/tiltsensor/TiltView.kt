package com.baek.tiltsensor

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.SensorEvent
import android.view.View

class TiltView(context: Context?) : View(context) {
    private val circlePaint: Paint = Paint()
    private val linePaint: Paint = Paint()

    private var cX: Float = 0f
    private var cY: Float = 0f

    private var xCoord: Float = 0f
    private var yCoord: Float = 0f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        cX = w / 2f
        cY = h / 2f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(cX, cY, 100f, linePaint)
        canvas?.drawCircle(xCoord + cX, yCoord + cY, 100f, circlePaint)

        canvas?.drawLine(cX - 20, cY, cX + 20, cY, linePaint)
        canvas?.drawLine(cX, cY - 20, cX, cY + 20, linePaint)
    }

    init {
        circlePaint.color = Color.CYAN
        linePaint.color = Color.BLACK
    }

    fun onSensorEvent(event: SensorEvent) {
        yCoord = event.values[0] * 20
        xCoord = event.values[1] * 20

        invalidate()
    }
}