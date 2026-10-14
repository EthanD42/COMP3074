
package com.example.lab5

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var latitudeText: TextView
    private lateinit var longitudeText: TextView
    private lateinit var locationButton: Button


    private val requestPermission =
        registerForActivityResult(

            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->

            val hasFinePermission =
                permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true


            val hasCoarsePermission =
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true


            if (hasFinePermission || hasCoarsePermission) {
                showLocation()
            } else {
                Toast.makeText(
                    this,
                    "Location permission denied.",
                    Toast.LENGTH_LONG

                ).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        latitudeText = findViewById(R.id.latitudeText)
        longitudeText = findViewById(R.id.longitudeText)
        locationButton = findViewById(R.id.getLocationButton)

        locationButton.setOnClickListener {
            checkPermission()
        }
    }

    private fun checkPermission() {

        val fineAllowed = ContextCompat.checkSelfPermission(

            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val coarseAllowed = ContextCompat.checkSelfPermission(

            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (fineAllowed || coarseAllowed)
        {
            showLocation()
        }
        else
        {
            requestPermission.launch(

                arrayOf(

                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }

    }




    private fun showLocation() {
        val fineAllowed = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED


        val coarseAllowed = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED


        if (!fineAllowed && !coarseAllowed) {
            checkPermission()
            return
        }

        try {
            val manager =

                getSystemService(LOCATION_SERVICE) as LocationManager

            val availableProviders = mutableListOf<String>()



            if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                availableProviders.add(LocationManager.GPS_PROVIDER)
            }


            if (coarseAllowed &&

                manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            ) {
                availableProviders.add(LocationManager.NETWORK_PROVIDER)
            }

            val lastLocation: Location? = availableProviders
                .mapNotNull { source ->

                    try {
                        manager.getLastKnownLocation(source)

                    } catch (_: SecurityException) {
                        null
                    }
                }
                .maxByOrNull { location -> location.time }



            if (lastLocation != null) {
                latitudeText.text = lastLocation.latitude.toString()
                longitudeText.text = lastLocation.longitude.toString()
            } else {
                Toast.makeText(
                    this,
                    "Location unavailable. try again.",
                    Toast.LENGTH_LONG
                ).show()
            }



        } catch (_: SecurityException) {
            Toast.makeText(
                this,
                "Location permission is required.",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
