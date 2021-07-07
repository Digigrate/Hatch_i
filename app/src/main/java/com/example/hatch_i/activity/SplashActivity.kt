package com.example.hatch_i.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.hatch_i.R
import com.example.hatch_i.common.Utils


class SplashActivity : AppCompatActivity() {

    private val SPLASH_DELAY: Long = 1000
    private var mDelayHandler: Handler? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity_main)
     //   supportActionBar?.setDisplayShowTitleEnabled(false)
     //   supportActionBar?.elevation = 0f

        //Initialize the Handler
        mDelayHandler = Handler()

        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)


    }


    internal val mRunnable: Runnable = Runnable {

        if (Utils.isConnectingToInternet(this)) {
            try {
                val intent = Intent(this, MainActivity::class.java)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                finish()
            } catch (err: Exception) {
                err.printStackTrace()
            }
        }
    }
}