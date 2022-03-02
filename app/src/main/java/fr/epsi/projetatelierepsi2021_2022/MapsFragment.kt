package fr.epsi.projetatelierepsi2021_2022

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.json.JSONObject

class MapsFragment() : Fragment() {

    lateinit var googleMap :GoogleMap



    @RequiresApi(Build.VERSION_CODES.N)
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

    fun callback(string_stores:String): OnMapReadyCallback {

        return OnMapReadyCallback { googleMap ->
            /**
             * Manipulates the map once available.
             * This callback is triggered when the map is ready to be used.
             * This is where we can add markers or lines, add listeners or move the camera.
             * In this case, we just add a marker near Sydney, Australia.
             * If Google Play services is not installed on the device, the user will be prompted to
             * install it inside the SupportMapFragment. This method will only be triggered once the
             * user has installed Google Play services and returned to the app.
             */
            if(string_stores !== null){
                val jsStores = JSONObject(string_stores)
                val stores = arrayListOf<Store>()
                val jsArrayStores = jsStores.getJSONArray("stores")
                for(i in 0 until jsArrayStores.length()){
                    val jsStore = jsArrayStores.getJSONObject(i)
                    val store = Store(jsStore.optString("storeId",""),
                        jsStore.optString("name",""),
                        jsStore.optString("description",""),
                        jsStore.optString("pictureStore",""),
                        jsStore.optString("address",""),
                        jsStore.optString("city",""),
                        jsStore.optString("longitude",""),
                        jsStore.optString("latitude",""),
                        jsStore.optString("zipcode",""))
                    stores.add(store)
                }

                for(i in 0 until jsArrayStores.length()){
                    val jsonStore= jsArrayStores.getJSONObject(i)
                    val store=MarkerOptions()
                    val cityLatLng = LatLng(jsonStore.optDouble("latitude", 0.0), jsonStore.optDouble("longitude",0.0))
                    store.title(jsonStore.optString("name"))
                    store.snippet(jsonStore.optString("address") + "-" + jsonStore.optString("zipcode") + " " + jsonStore.optString("city"))
                    store.position(cityLatLng)
                    googleMap.addMarker(store)
                }

                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(48.854885,2.338646),5f))

                googleMap.setOnMapClickListener {
                    (activity as BaseActivity).showToast(it.toString())
                }


                googleMap.setOnMarkerClickListener(GoogleMap.OnMarkerClickListener { marker -> // on marker click we are getting the title of our marker
                    // which is clicked and displaying it in a toast message.
                    for(i in 0..jsArrayStores.length()-1){
                        val jsonCity= jsArrayStores.getJSONObject(i)

                        if(jsonCity.optString("name") == marker.title){
                            val data = jsArrayStores.getJSONObject(i)
                            val intent = Intent(this.context, FicheMagasinActivity::class.java)
                            intent.putExtra("magasin_nom", data.optString("name"))
                            intent.putExtra("image", data.optString("pictureStore"))
                            intent.putExtra("address", data.optString("address"))
                            intent.putExtra("zipcode", data.optString("zipcode"))
                            intent.putExtra("city", data.optString("city"))
                            intent.putExtra("description", data.optString("description"))
                            this.context?.startActivity(intent)
                        }
                    }
                    false
                })

                googleMap.setOnInfoWindowClickListener {
                    (activity as BaseActivity).showToast(it.toString())
                }
                this.googleMap=googleMap
                locationPermissionRequest.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))

            } else {
                Log.d("string stores ", "string stores null")
            }
        }
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
        val string_stores = getArguments()?.getString("stores");
        if (string_stores != null) {
            mapFragment?.getMapAsync(callback(string_stores))
        }
    }

}