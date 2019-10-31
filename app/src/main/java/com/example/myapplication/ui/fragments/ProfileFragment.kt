package com.example.myapplication.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private lateinit var userData : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getUserData()
        toolbarProfile.inflateMenu(R.menu.profile_menu)
    }

    private fun getUserData(){
        userData = activity?.getSharedPreferences("UserData", Context.MODE_PRIVATE) ?: return
        profile_nickname.text = userData.getString("UserName","Username")
        profile_description.text = userData.getString("UserDescription","Description")
    }

}