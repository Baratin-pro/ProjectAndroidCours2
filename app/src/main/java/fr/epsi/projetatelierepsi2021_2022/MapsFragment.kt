package fr.epsi.projetatelierepsi2021_2022

import android.Manifest
import android.annotation.SuppressLint
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.json.JSONObject

class MapsFragment : Fragment() {

    lateinit var googleMap :GoogleMap

    @SuppressLint("MissingPermission")
    val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                // Precise location access granted.
                googleMap.isMyLocationEnabled=true
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                // Only approximate location access granted.
                googleMap.isMyLocationEnabled=true
            } else -> {
            // No location access granted.
        }
        }
    }


    val cities="{\"cities\":[{\"city\":\"Bordeaux\",\"lan\":44.847807,\"lng\":-0.579472},\n" +
            "{\"city\":\"Pau\",\"lan\":43.293295,\"lng\":-0.363570},\n" +
            "{\"city\":\"Nantes\",\"lan\":47.215585,\"lng\":-1.554908},\n" +
            "{\"city\":\"Paris\",\"lan\":48.854885,\"lng\":2.338646},\n" +
            "{\"city\":\"Lille\",\"lan\":50.608719,\"lng\":3.063295},\n" +
            "{\"city\":\"Marseille\",\"lan\":43.293551,\"lng\":5.377397},\n" +
            "{\"city\":\"Nice\",\"lan\":43.701680,\"lng\":7.260711},\n" +
            "{\"city\":\"Lyon\",\"lan\":45.759132,\"lng\":4.834604},\n" +
            "{\"city\":\"Montpellier\",\"lan\":43.586120,\"lng\":3.896094},\n" +
            "{\"city\":\"Toulouse\",\"lan\":43.533513,\"lng\":1.411209},\n" +
            "{\"city\":\"Brest\",\"lan\":48.389353,\"lng\":-4.488616},\n" +
            "{\"city\":\"Limoges\",\"lan\":45.838771,\"lng\":1.262108},\n" +
            "{\"city\":\"Clermont-Ferrand\",\"lan\":45.780535,\"lng\":3.093242},\n" +
            "{\"city\":\"Tours\",\"lan\":47.404355,\"lng\":0.688930},\n" +
            "{\"city\":\"Strasbourg\",\"lan\":48.540395,\"lng\":7.727753}]}";

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */

        val jsonCities= JSONObject(cities)
        val items=jsonCities.getJSONArray("cities")

        for(i in 0..items.length()-1){
            val jsonCity= items.getJSONObject(i)
            val city=MarkerOptions()
            val cityLatLng = LatLng(jsonCity.optDouble("lan", 0.0), jsonCity.optDouble("lng",0.0))
            city.title(jsonCity.optString("city"))
            city.position(cityLatLng)
            googleMap.addMarker(city)
        }

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(48.854885,2.338646),5f))

        googleMap.setOnMapClickListener {
            (activity as BaseActivity).showToast(it.toString())
        }

        googleMap.setOnInfoWindowClickListener {
            (activity as BaseActivity).showToast(it.toString())
        }
        this.googleMap=googleMap
        locationPermissionRequest.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}