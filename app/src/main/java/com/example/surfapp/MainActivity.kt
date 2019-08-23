package com.example.surfapp

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        searchView.setOnSearchClickListener {
            if (titleText.text==""){
                titleText.text = resources.getString(R.string.main_title)
            } else {
                titleText.text = ""
            }
        }

        feed.setOnClickListener{

        }
    }

}