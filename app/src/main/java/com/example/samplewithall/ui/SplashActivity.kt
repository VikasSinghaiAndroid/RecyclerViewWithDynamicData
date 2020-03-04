package com.example.samplewithall.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.samplewithall.R
import com.log4k.d
import dagger.android.DaggerActivity

class SplashActivity : DaggerActivity() {
    private var mDelayHandler: Handler? = null
    private val splashDelay: Long = 2000 //5 seconds

    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {

            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        d("This is my log")
        //Initialize the Handler
        mDelayHandler = Handler()

        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, splashDelay)

    }

    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }
}