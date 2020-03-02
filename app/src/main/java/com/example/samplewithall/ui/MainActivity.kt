package com.example.samplewithall.ui

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.samplewithall.R
import dagger.android.support.DaggerAppCompatActivity


class MainActivity : DaggerAppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.container)

    }
}
