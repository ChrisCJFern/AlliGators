package com.example.alligatorstours

import android.content.Intent
import android.graphics.Camera
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.alligatorstours.databinding.ActivityTourBinding
import com.google.android.gms.maps.*

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
import androidx.core.content.ContextCompat
import com.example.alligatorstours.PermissionUtils.PermissionDeniedDialog.Companion.newInstance
import com.example.alligatorstours.PermissionUtils.isPermissionGranted
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener
import com.google.android.gms.maps.GoogleMap.OnMyLocationClickListener

import android.content.pm.ApplicationInfo
import android.graphics.Color
import android.widget.Button
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.libraries.places.api.Places
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.Executors
import android.os.Handler
import android.os.Looper

class TourActivity : AppCompatActivity(),
    OnMyLocationButtonClickListener,
    OnMyLocationClickListener,
    OnMapReadyCallback,
    OnRequestPermissionsResultCallback{

    private var permissionDenied = false

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityTourBinding
    private var locsMap = HashMap<String, LatLng>()

    //Executor and Handler for asynchronous call to Directions API
    private val myExecutor = Executors.newSingleThreadExecutor()
    private val myHandler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTourBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Fetching wrapped API_KEY
        val ai: ApplicationInfo = applicationContext.packageManager
            .getApplicationInfo(applicationContext.packageName, PackageManager.GET_META_DATA)
        val value = ai.metaData["com.google.android.geo.API_KEY"]
        val apiKey = value.toString()

        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, apiKey)
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        locsMap["Reitz Union"] = LatLng(29.6457017443439, -82.3486471463526)
        locsMap["Plaza of the Americas"] = LatLng(29.650671325915198, -82.34282613101179)
        locsMap["Century Tower"] = LatLng(29.6490014716868, -82.34281015857091)
        locsMap["Career Connections Center"] = LatLng(29.64642963107201, -82.34828188126144)
        locsMap["College of Journalism"] = LatLng(29.648301666155717, -82.34749555984703)
        locsMap["Marston Science Library"] = LatLng(29.648217193648133, -82.34377838868227)
        locsMap["Ben Hill Griffin Stadium"] = LatLng(29.650140294458456, -82.34868274578238)
        locsMap["The Hub"] = LatLng(29.64846085003454, -82.34597309976017)
        locsMap["The Potato (Turlington Rock)"] = LatLng(29.649098607921967, -82.34373874586767)
        locsMap["The French Fries"] = LatLng(29.648003962416666, -82.34434626618705)
        locsMap["Tigert Hall"] = LatLng(29.649675380428793, -82.33973017266058)
        locsMap["Field and Fork"] = LatLng(29.64765173130951, -82.34476201756253)
        locsMap["Criser Hall"] = LatLng(29.650324806582304, -82.34178108800249)
        locsMap["Broward Hall"] = LatLng(29.64725997084334, -82.34125671498957)
        locsMap["Stephen C. O'Connell Center"] = LatLng(29.64959081557347, -82.35108237081205)

        //Update Direction polyline button click listener function
        val dirbtn = findViewById<Button>(R.id.directions)
        dirbtn.setOnClickListener{
            mapFragment.getMapAsync {
                mMap = it
                val origin = intent.extras!!.getString("ORIGIN")
                val originLocation = LatLng(locsMap[origin]!!.latitude, locsMap[origin]!!.longitude)
                mMap.addMarker(MarkerOptions().position(originLocation).title(origin))
                val destination = intent.extras!!.getString("DESTINATION")
                val destinationLocation = LatLng(locsMap[destination]!!.latitude, locsMap[destination]!!.longitude)
                mMap.addMarker(MarkerOptions().position(destinationLocation).title(destination))
                val urll = getDirectionURL(originLocation, destinationLocation, apiKey)
                getDirection(urll)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(originLocation, 14F))
            }
        }

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        googleMap.setOnMyLocationButtonClickListener(this)
        googleMap.setOnMyLocationClickListener(this)
        enableMyLocation()

        val locList = intent.getStringArrayListExtra("EXTRA_LOCATIONS")

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
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.setAllGesturesEnabled(true)
    }

    //Function to get Directions API URL
    private fun getDirectionURL(origin:LatLng, dest:LatLng, secret: String) : String{
        val locList = intent.getStringArrayListExtra("EXTRA_LOCATIONS")
        var waypoints = "&waypoints=optimize:true|";
        if (locList != null) {
            for (loc in locList) {
                waypoints += "via:${locsMap[loc]!!.latitude},${locsMap[loc]!!.longitude}|"
            }
        }

        return "https://maps.googleapis.com/maps/api/directions/json?origin=${origin.latitude},${origin.longitude}" +
                "&destination=${dest.latitude},${dest.longitude}" +
                waypoints +
                "&mode=walking" +
                "&key=$secret"
    }

    //Function used to decode the polyline that is returned by the Directions API call
    fun decodePolyline(encoded: String): List<LatLng> {
        val poly = ArrayList<LatLng>()
        var index = 0
        val len = encoded.length
        var lat = 0
        var lng = 0
        while (index < len) {
            var b: Int
            var shift = 0
            var result = 0
            do {
                b = encoded[index++].code - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lat += dlat
            shift = 0
            result = 0
            do {
                b = encoded[index++].code - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lng += dlng
            val latLng = LatLng((lat.toDouble() / 1E5),(lng.toDouble() / 1E5))
            poly.add(latLng)
        }
        return poly
    }

    //Function to asynchronously call the Directions API
    private fun getDirection(url : String){
        myExecutor.execute {
            val client = OkHttpClient()
            val request = Request.Builder().url(url).build()
            val response = client.newCall(request).execute()
            val data = response.body!!.string()

            val result =  ArrayList<List<LatLng>>()
            try{
                val respObj = Gson().fromJson(data,MapData::class.java)
                val path =  ArrayList<LatLng>()
                for (i in 0 until respObj.routes[0].legs[0].steps.size){
                    path.addAll(decodePolyline(respObj.routes[0].legs[0].steps[i].polyline.points))
                }
                result.add(path)
            }catch (e:Exception){
                e.printStackTrace()
            }
            myHandler.post{
                val lineoption = PolylineOptions()
                for (i in result.indices){
                    lineoption.addAll(result[i])
                    lineoption.width(10f)
                    lineoption.color(Color.BLUE)
                    lineoption.geodesic(true)
                }
                mMap.addPolyline(lineoption)
            }
        }
    }


    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    @SuppressLint("MissingPermission")
    private fun enableMyLocation() {

        // [START maps_check_location_permission]
        // 1. Check if permissions are granted, if so, enable the my location layer
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mMap.isMyLocationEnabled = true
            return
        }

        // 2. If a permission rationale dialog should be shown
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) || ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        ) {
            PermissionUtils.RationaleDialog.newInstance(
                LOCATION_PERMISSION_REQUEST_CODE, true
            ).show(supportFragmentManager, "dialog")
            return
        }

        // 3. Otherwise, request permission
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            LOCATION_PERMISSION_REQUEST_CODE
        )
        // [END maps_check_location_permission]
    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT)
            .show()
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false
    }

    override fun onMyLocationClick(location: Location) {
        Toast.makeText(this, "Current location:\n$location", Toast.LENGTH_LONG)
            .show()
    }

    // [START maps_check_location_permission_result]
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            super.onRequestPermissionsResult(
                requestCode,
                permissions,
                grantResults
            )
            return
        }

        if (isPermissionGranted(
                permissions,
                grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) || isPermissionGranted(
                permissions,
                grantResults,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        ) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation()
        } else {
            // Permission was denied. Display an error message
            // [START_EXCLUDE]
            // Display the missing permission error dialog when the fragments resume.
            permissionDenied = true
            // [END_EXCLUDE]
        }
    }

    // [END maps_check_location_permission_result]
    override fun onResumeFragments() {
        super.onResumeFragments()
        if (permissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError()
            permissionDenied = false
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private fun showMissingPermissionError() {
        newInstance(true).show(supportFragmentManager, "dialog")
    }

    companion object {
        /**
         * Request code for location permission request.
         *
         * @see .onRequestPermissionsResult
         */
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
}