package com.example.hatch_i.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.example.hatch_i.R
import com.example.hatch_i.common.Utils
import com.example.hatch_i.storageHelpers.PreferenceHelper
import com.google.android.material.snackbar.Snackbar


class SplashActivity : AppCompatActivity() {

    private val SPLASH_DELAY: Long = 5000
    private var mDelayHandler: Handler? = null

    lateinit var rl_root_view : RelativeLayout
    var token:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity_main)
     //   supportActionBar?.setDisplayShowTitleEnabled(false)
     //   supportActionBar?.elevation = 0f
        rl_root_view = findViewById(R.id.rl_root_view)
        if(!Utils.isConnectingToInternet(this@SplashActivity)) {
            Utils.showIndefiniteSnackBar(
                rl_root_view,
                "You're offline, Please check your network connection."
            )
        }
        token = PreferenceHelper.getStringPreference(this@SplashActivity, "token").toString()
        //Initialize the Handler
        mDelayHandler = Handler()

        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)


    }


    internal val mRunnable: Runnable = Runnable {

        startapp()

    }

    private fun startapp() {
        if (Utils.isConnectingToInternet(this@SplashActivity)) {
            if(token.equals("null")){
                try {
                    val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    finish()
                } catch (err: Exception) {
                    err.printStackTrace()
                }
            }else{
                try {
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    finish()
                } catch (err: Exception) {
                    err.printStackTrace()
                }

            }


        }else{
            Utils.showIndefiniteSnackBar(
                rl_root_view,
                "You're offline, Please check your network connection."
            )
        }
    }

    private fun snack(text: String) {
        val snackbar = Snackbar.make(rl_root_view, text, Snackbar.LENGTH_LONG)
        snackbar.show()
    }
}