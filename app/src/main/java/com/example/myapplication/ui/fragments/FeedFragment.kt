package com.example.myapplication.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapters.MemeAdapter
import com.example.myapplication.models.MemeDto
import com.example.myapplication.retrofit.RetrofitClient
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_feed.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedFragment : Fragment(), Callback<List<MemeDto>> {

    lateinit var memeAdapter : MemeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_feed, container, false)
        return view
    }

    override fun onResume() {
        super.onResume()
        loadMemes()
    }

    private fun loadMemes() {
        faded.alpha=0F
        loadPB.visibility=View.VISIBLE
        val call = RetrofitClient.memesService.getMemes()
        call.enqueue(this)
    }

    private fun initRecyclerView(memes: List<MemeDto>){
        memeAdapter = MemeAdapter()
        memeAdapter.setItems(memes)
        feedRecycler.adapter = memeAdapter
        feedRecycler.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        faded.alpha=1F
        loadPB.visibility=View.INVISIBLE
    }

    override fun onFailure(call: Call<List<MemeDto>>, t: Throwable) {
        showSnackBar()
        error.visibility=View.VISIBLE
    }

    override fun onResponse(call: Call<List<MemeDto>>, response: Response<List<MemeDto>>) {
        val memes = response.body()
        error.visibility=View.INVISIBLE
        for(meme in memes.orEmpty()){
            println(meme.toString())
        }
        if (memes != null) {
            initRecyclerView(memes)
        }
    }

    private fun showSnackBar(){
        val snackBar = Snackbar.make(loginLayout, R.string.connection_error, Snackbar.LENGTH_SHORT)
        snackBar.view.setBackgroundColor(resources.getColor(R.color.snackbg))
        snackBar.setActionTextColor(Color.WHITE)
        snackBar.show()
    }
}