package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.models.MemeDto
import com.example.myapplication.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedFragment : Fragment(), Callback<List<MemeDto>> {

    private val memeFragments: List<MemeBlockFragment>
        get() {
            return memeFragments
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        retainInstance = true
        loadMemes()
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    private fun loadMemes() {
        val call = RetrofitClient.memesService.getMemes()
        call.enqueue(this)
    }

    override fun onFailure(call: Call<List<MemeDto>>, t: Throwable) {
        println("Failed to load memes: " + t.message)
    }

    override fun onResponse(call: Call<List<MemeDto>>, response: Response<List<MemeDto>>) {
        val memes = response.body()
        println(memes.toString())
        for (i in memes.orEmpty()) {

        }
    }
}