package com.example.alligatorstours

import android.content.Intent
import android.graphics.Camera
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.alligatorstours.databinding.ActivityTourBinding

class TourActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityTourBinding

    val locsMap = HashMap<String, LatLng>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTourBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val locList = intent.getStringArrayListExtra("EXTRA_LOCATIONS")

        locsMap["Reitz Union"] = LatLng(29.6457017443439, -82.3486471463526)
        locsMap["Plaza of the Americas"] = LatLng(29.650671325915198, -82.34282613101179)
        locsMap["Century Tower"] = LatLng(29.6490014716868, -82.34281015857091)
        locsMap["Career Connections Center"] = LatLng(29.64642963107201, -82.34828188126144)
        locsMap["College of Journalism"] = LatLng(29.648301666155717, -82.34749555984703)
        locsMap["Marston Science Library"] = LatLng(29.648217193648133, -82.34377838868227)
        locsMap["Ben Hill Griffin Stadium"] = LatLng(29.650140294458456, -82.34868274578238)
        locsMap["The Hub"] = LatLng(29.64846085003454, -82.34597309976017)

        if (locList != null) {
            Log.d("count", "locList has ${locList.size} elements")
        }

        // Add a marker at UF and move the camera
        val uf = LatLng(29.6438, -82.3548)
        mMap.addMarker(MarkerOptions()
            .position(uf)
            .title("Go Gators!"))

        if (locList != null) {
            for (loc in locList) {
                mMap.addMarker(MarkerOptions()
                    .position(locsMap[loc]!!)
                    .title(loc))
            }
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(uf, 14.0f))


    }
}