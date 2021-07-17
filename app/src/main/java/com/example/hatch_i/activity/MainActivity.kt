package com.example.hatch_i.activity

import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
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
import com.example.hatch_i.common.Utils
import com.example.hatch_i.fragment.*
import com.example.hatch_i.storageHelpers.PreferenceHelper
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var bottomNavigationView: BottomNavigationView
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    lateinit var tv_username: TextView
     var name: String? = "asd"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle!!)
        actionBarDrawerToggle!!.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        assert(navView != null)
        navView.setNavigationItemSelectedListener(this@MainActivity)
        initClickListeners()
        addFragment(HomeFragment(), false, HomeFragment::class.java.simpleName)
        val headerView: View = navView.getHeaderView(0)
        tv_username = headerView.findViewById(R.id.tv_username)
        name = PreferenceHelper.getStringPreference(this@MainActivity,"username")
        tv_username.setText("Hi "+name)

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




    fun addFragment(fragment: Fragment, addToBackStack: Boolean, tag: String) {
        val manager: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = manager.beginTransaction()

        if (addToBackStack) {
            ft.addToBackStack(tag)
        }
        ft.replace(R.id.frame_container, fragment, tag)
        ft.commitAllowingStateLoss()
    }

    public fun setFragment() {
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
                        DetailsListFragment(),
                        true,
                        DetailsListFragment::class.java.simpleName
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                bottomNavigationView.selectedItemId = R.id.action_home
                addFragment(
                    HomeFragment(),
                    true,
                    HomeFragment::class.java.simpleName
                )
                drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }
            R.id.nav_logout -> {
                Utils.showDialog(
                    "Are you sure you want to logout?",
                    DialogInterface.OnClickListener { dialog, which ->
                        when (which) {
                            DialogInterface.BUTTON_POSITIVE -> {
                                dialog.dismiss()
                                Utils.logoutclearperf(this@MainActivity)
                            }
                            DialogInterface.BUTTON_NEGATIVE -> {
                                dialog.dismiss()
                            }
                        }
                    }, this
                )
                return true
            }
        }
        return actionBarDrawerToggle!!.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            bottomNavigationView.selectedItemId = R.id.action_home
        } else if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers()
        } else {
            finishAffinity()
        }
    }
}