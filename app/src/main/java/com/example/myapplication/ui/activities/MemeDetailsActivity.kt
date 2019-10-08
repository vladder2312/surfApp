package com.example.myapplication.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_meme_details.*


class MemeDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meme_details)
        mdToolbar.title=""
        setSupportActionBar(mdToolbar)
        loadMeme()

        close_meme_btn.setOnClickListener {
            super.finish()
        }

        authorLogo.setOnClickListener {
            // TODO("Открыть профиль")
        }

        authorNick.setOnClickListener {
            //TODO("Открыть профиль")
        }

        shareDetailMeme.setOnClickListener {
            //TODO("Поделиться мемом")
        }
    }

    private fun loadMeme(){
        titleFullMeme.text = intent.getStringExtra("title")
        descriptionFullMeme.text = intent.getStringExtra("description")
        Picasso.get().load(intent.getStringExtra("imageUtl")).into(imageFullMeme)
        dateFullMeme.text = intent.getStringExtra("createdDate")
    }
}