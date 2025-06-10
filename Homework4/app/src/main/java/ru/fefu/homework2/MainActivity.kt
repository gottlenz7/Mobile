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
                R.id.activity -> showActivitiesFragment()
                R.id.profile -> showProfileFragment()
                else -> false
            }
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, ActivitiesFragment(), "ACTIVITIES_TAG")
                .commit()
        }
    }

    private fun showActivitiesFragment(): Boolean {
        val profileFragment = supportFragmentManager.findFragmentByTag("PROFILE_TAG")
        val activitiesFragment = supportFragmentManager.findFragmentByTag("ACTIVITIES_TAG")

        supportFragmentManager.beginTransaction().apply {
            if (profileFragment != null) hide(profileFragment)
            if (activitiesFragment != null) {
                show(activitiesFragment)
            } else {
                add(R.id.fragment_container, ActivitiesFragment(), "ACTIVITIES_TAG")
            }
        }.commit()
        return true
    }

    private fun showProfileFragment(): Boolean {
        val activitiesFragment = supportFragmentManager.findFragmentByTag("ACTIVITIES_TAG")
        val profileFragment = supportFragmentManager.findFragmentByTag("PROFILE_TAG")

        supportFragmentManager.beginTransaction().apply {
            if (activitiesFragment != null) hide(activitiesFragment)
            if (profileFragment != null) {
                show(profileFragment)
            } else {
                add(R.id.fragment_container, ProfileFragment(), "PROFILE_TAG")
            }
        }.commit()
        return true
    }
}