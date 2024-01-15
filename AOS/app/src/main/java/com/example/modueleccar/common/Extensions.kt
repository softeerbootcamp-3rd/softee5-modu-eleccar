package com.example.modueleccar.common

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.WindowManager
import com.example.modueleccar.R

fun Context.dialogResize(dialog: Dialog, width: Float, height: Float){
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

    if (Build.VERSION.SDK_INT < 30){
        val display = windowManager.defaultDisplay
        val size = Point()

        display.getSize(size)

        val window = dialog.window

        val x = (size.x * width).toInt()
        val y = (size.y * height).toInt()

        window?.setLayout(x, y)

    }else{
        val rect = windowManager.currentWindowMetrics.bounds

        val window = dialog.window
        val x = (rect.width() * width).toInt()
        val y = (rect.height() * height).toInt()

        window?.setLayout(x, y)
    }
    dialog.window?.setBackgroundDrawable(getDrawable(R.drawable.radius_12dp))
    //dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
}

fun convertTimeString(startTime : Int, endTime: Int): String{
    val startString = String.format("%02d", startTime)
    val endString = String.format("%02d", endTime)
    return "${startString}:00 - ${endString}:00"
}

