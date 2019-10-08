package com.example.myapplication.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.models.MemeDto
import com.example.myapplication.ui.activities.MainActivity
import com.example.myapplication.ui.activities.MemeDetailsActivity
import kotlinx.android.synthetic.main.fragment_meme_block.*

class MemeBlockFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance=true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        retainInstance=true
        return inflater.inflate(R.layout.fragment_meme_block, container, false)
    }

}