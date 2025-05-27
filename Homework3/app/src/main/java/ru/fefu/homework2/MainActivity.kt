package ru.fefu.homework2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.activity -> showActivityFragment()
                R.id.profile -> showProfileFragment()
                else -> false
            }
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, ActivityFragment(), "ACTIVITY_TAG")
                .commit()
        }
    }

    private fun showActivityFragment(): Boolean {
        val profileFragment = supportFragmentManager.findFragmentByTag("PROFILE_TAG")
        val activityFragment = supportFragmentManager.findFragmentByTag("ACTIVITY_TAG")

        supportFragmentManager.beginTransaction().apply {
            if (profileFragment != null) hide(profileFragment)
            if (activityFragment != null) {
                show(activityFragment)
            } else {
                add(R.id.fragment_container, ActivityFragment(), "ACTIVITY_TAG")
            }
        }.commit()
        return true
    }

    private fun showProfileFragment(): Boolean {
        val activityFragment = supportFragmentManager.findFragmentByTag("ACTIVITY_TAG")
        val profileFragment = supportFragmentManager.findFragmentByTag("PROFILE_TAG")

        supportFragmentManager.beginTransaction().apply {
            if (activityFragment != null) hide(activityFragment)
            if (profileFragment != null) {
                show(profileFragment)
            } else {
                add(R.id.fragment_container, ProfileFragment(), "PROFILE_TAG")
            }
        }.commit()
        return true
    }
}