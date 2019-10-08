package com.example.myapplication.ui.activities

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat
import com.example.myapplication.R
import com.example.myapplication.ui.fragments.AddMemeFragment
import com.example.myapplication.ui.fragments.FeedFragment
import com.example.myapplication.ui.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val feed = FeedFragment()
    private val add = AddMemeFragment()
    private val profile = ProfileFragment()
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val transaction = supportFragmentManager.beginTransaction()
        when(item.itemId){
            R.id.action_feed -> {
                transaction.replace(R.id.fragmentHolder,feed)
                setSupportActionBar(mainToolbar)
                item.isChecked = true
            }
            R.id.action_addMeme -> {
                transaction.replace(R.id.fragmentHolder,add)
                item.isChecked = true
            }
            R.id.action_profile -> {
                transaction.replace(R.id.fragmentHolder,profile)
                item.isChecked = true
            }
        }
        transaction.commit()
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainToolbar.title=""
        bottom_navigation.setOnNavigationItemSelectedListener (onNavigationItemSelectedListener)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragmentHolder,feed)
        transaction.commit()

        search.setOnSearchClickListener {
            mainTitle.text=""
        }

    }

}
