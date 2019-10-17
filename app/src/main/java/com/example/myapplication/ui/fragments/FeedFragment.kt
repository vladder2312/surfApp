package com.example.myapplication.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication.data.adapters.ChangeListener
import com.example.myapplication.data.adapters.MemeAdapter
import com.example.myapplication.data.models.MemeDto
import com.example.myapplication.data.retrofit.RetrofitClient
import com.example.myapplication.ui.activities.MemeDetailsActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_feed.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit


class FeedFragment : Fragment(), ChangeListener {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadMemes()
        swipeRefresh.setOnRefreshListener{
            loadMemes()
            swipeRefresh.isRefreshing=false
        }
    }

    private fun loadMemes() {
        faded.alpha=0F
        loadPB.visibility=View.VISIBLE
        val call = RetrofitClient.memesService
            .getMemes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .timeout(5,TimeUnit.SECONDS)
            .subscribe({
                val memes = it
                error.visibility=View.INVISIBLE
                if (memes != null) {
                    initRecyclerView(memes)
                }
            }, {
                showSnackBar()
                error.visibility=View.VISIBLE
            })
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


    //TODO("Не работает")
    @SuppressLint("ResourceAsColor")
    private fun showSnackBar(){
        val snackBar = Snackbar.make(fragmentHolder, R.string.connection_error, Snackbar.LENGTH_SHORT)
        snackBar.view.setBackgroundColor(R.color.snackbg)
        snackBar.setActionTextColor(Color.WHITE)
        snackBar.show()
        loadPB.visibility=View.INVISIBLE
    }
}