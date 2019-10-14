package com.example.myapplication.ui.fragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication.adapters.ChangeListener
import com.example.myapplication.adapters.MemeAdapter
import com.example.myapplication.models.MemeDto
import com.example.myapplication.retrofit.RetrofitClient
import com.example.myapplication.ui.activities.MemeDetailsActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_feed.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapplication.R


class FeedFragment : Fragment(), Callback<List<MemeDto>>, ChangeListener {

    override fun sourceChanged(position: Int) {
        Log.d(tag,"from feed = ${memeAdapter[position]}")
        val intent = Intent(context, MemeDetailsActivity::class.java)
        intent.putExtra("id",memeAdapter[position].id)
        intent.putExtra("title",memeAdapter[position].title)
        intent.putExtra("description",memeAdapter[position].description)
        intent.putExtra("imageUtl",memeAdapter[position].photoUtl)
        intent.putExtra("isFavourite",memeAdapter[position].isFavorite)
        intent.putExtra("createdDate", memeAdapter.getFormattedDate(memeAdapter[position].createdDate))
        startActivity(intent)
    }

    lateinit var memeAdapter : MemeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        retainInstance = true
    }

    //TODO("Тулбары не работают")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        retainInstance=true

        val view = inflater.inflate(R.layout.fragment_feed, container, false)
        val toolbar = view.findViewById(R.id.mainToolbar) as? Toolbar
        val activity = activity as AppCompatActivity?
        activity!!.setSupportActionBar(toolbar)
        val actionBar = activity.supportActionBar
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        swipeRefresh.setOnRefreshListener{
            loadMemes()
            swipeRefresh.isRefreshing=false
        }
        super.onActivityCreated(savedInstanceState)
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
        memeAdapter.addListener(this)
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
        if (memes != null) {
            initRecyclerView(memes)
        }
    }

    //TODO("Тут крашится")
    private fun showSnackBar(){
        val snackBar = Snackbar.make(loginLayout, R.string.connection_error, Snackbar.LENGTH_SHORT)
        snackBar.view.setBackgroundColor(resources.getColor(R.color.snackbg))
        snackBar.setActionTextColor(Color.WHITE)
        snackBar.show()
    }
}