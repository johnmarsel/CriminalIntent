package com.bignerdranch.android.criminalintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import java.util.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(),
    CrimeListFragment.Callbacks, CrimeFragment.Callbacks{

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onCrimeSelected(crimeId: UUID) {
        val args = Bundle().apply {
            putSerializable(ARG_CRIME_ID, crimeId)
        }
        navController.navigate(R.id.action_crimeListFragment_to_crimeFragment, args)
    }

    override fun onCrimeDeleted() {
        navController.popBackStack()
    }
}