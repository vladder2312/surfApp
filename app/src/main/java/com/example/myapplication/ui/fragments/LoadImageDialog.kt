package com.example.myapplication.ui.fragments

import android.app.Activity
import android.app.Dialog
import android.view.Window
import android.widget.TextView
import com.example.myapplication.R

object LoadImageDialog {

    fun showDialog(activity: Activity, onCameraClick: () -> Unit, onGalleryClick: () -> Unit) {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_load_image)

        val gallery = dialog.findViewById(R.id.text_gallery) as TextView
        gallery.setOnClickListener {
            onGalleryClick()
            dialog.hide()
        }
        val camera = dialog.findViewById(R.id.text_camera) as TextView
        camera.setOnClickListener {
            onCameraClick()
            dialog.hide()
        }
        dialog.show()
    }
}