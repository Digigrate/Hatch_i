package com.example.hatch_i.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.ui.AppBarConfiguration
import com.example.garinnoglobal.fragment.DetailsFragment
import com.example.hatch_i.R
import com.example.hatch_i.apiclient.ApiClient
import com.example.hatch_i.common.Utils
import com.example.hatch_i.fragment.HistoryFragment
import com.example.hatch_i.fragment.HomeFragment
import com.example.hatch_i.fragment.NotificationFragment
import com.example.hatch_i.fragment.TipsFragment
import com.example.hatch_i.model.Login
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var bottomNavigationView: BottomNavigationView
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    var actionBarDrawerToggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        setSupportActionBar(toolbar)
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout,R.string.nav_open, R.string.nav_close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle!!)
        actionBarDrawerToggle!!.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        initClickListeners()
        addFragment(HomeFragment(), false, HomeFragment::class.java.simpleName)
        getData()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.action_noification -> {
                addFragment(
                    NotificationFragment(),
                    true,
                    NotificationFragment::class.java.simpleName
                )
                return true
            }

        }
        return actionBarDrawerToggle!!.onOptionsItemSelected(item)
    }


//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return if (actionBarDrawerToggle!!.onOptionsItemSelected(item)) {
//            true
//        } else super.onOptionsItemSelected(item)
//
//    }

    private fun getData() {
        val call: Call<List<Login>> = ApiClient.getClient.getLogininfo()
        call.enqueue(object : Callback<List<Login>> {

            override fun onResponse(call: Call<List<Login>>?, response: Response<List<Login>>?) {
                Log.e("Respponse",response.toString())
            //    progerssProgressDialog.dismiss()
             //   dataList.addAll(response!!.body()!!)
              //  recyclerView.adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<Login>>?, t: Throwable?) {
                Log.e("Failure",t.toString())
                //progerssProgressDialog.dismiss()
            }

        })
    }

    fun addFragment(fragment: Fragment, addToBackStack: Boolean, tag: String) {
        val manager: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = manager.beginTransaction()

        if (addToBackStack) {
            ft.addToBackStack(tag)
        }
        ft.replace(R.id.frame_container, fragment, tag)
        ft.commitAllowingStateLoss()
    }
    public fun setFragment(){
        addFragment(
            DetailsFragment(),
            true,
            DetailsFragment::class.java.simpleName
        )
    }
    private fun initClickListeners() {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {


                R.id.action_home -> {
                    bottomNavigationView.getMenu().getItem(0)
                        .setIcon(R.drawable.home);
                    addFragment(
                        HomeFragment(),
                        true,
                        HomeFragment::class.java.simpleName
                    )
                }

                R.id.action_details -> {
                    bottomNavigationView.getMenu().getItem(1)
                        .setIcon(R.drawable.details)
                    addFragment(
                        DetailsFragment(),
                        true,
                        DetailsFragment::class.java.simpleName
                    )

                }

                R.id.action_history -> {
                    bottomNavigationView.getMenu().getItem(2)
                        .setIcon(R.drawable.history)
                    addFragment(
                        HistoryFragment(),
                        true,
                        HistoryFragment::class.java.simpleName
                    )
                }

                R.id.action_tips -> {
                    bottomNavigationView.getMenu().getItem(3)
                        .setIcon(R.drawable.navigation)
                        .setTitle("Tips")
                    addFragment(
                        TipsFragment(),
                        true,
                        TipsFragment::class.java.simpleName
                    )
                }
            }
            true
        }



    }


}