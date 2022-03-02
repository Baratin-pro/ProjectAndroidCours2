package fr.epsi.projetatelierepsi2021_2022

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class FragmentActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("fragment", "***")
        setContentView(R.layout.activity_fragmet)
        showPictureAccount()

        val tabCarte=findViewById<TextView>(R.id.textViewCarte)
        val tabOffres=findViewById<TextView>(R.id.textViewOffres)
        val tabMagasin=findViewById<TextView>(R.id.textViewMagasins)
        showBack()
        getAccount()

        tabOffres.setOnClickListener(View.OnClickListener{
            showTabOffres()
        })

        tabMagasin.setOnClickListener(View.OnClickListener {
            showTabMagasins()
        })

        tabCarte.setOnClickListener(View.OnClickListener {
            showTabCarte()
        })

        setHeaderTitle("Application")
        showTabCarte()
    }

    private fun showTabCarte() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setReorderingAllowed(true)
        fragmentTransaction.addToBackStack("Carte") // name can be null
        fragmentTransaction.replace(R.id.fragment_container, CarteActivity::class.java, null)
        fragmentTransaction.commit()
    }

    private fun showTabOffres() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setReorderingAllowed(true)
        fragmentTransaction.addToBackStack("Offres") // name can be null
        fragmentTransaction.replace(R.id.fragment_container, FragmentOffres::class.java, null)
        fragmentTransaction.commit()
    }

    private fun showTabMagasins() {
        /*val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        val mRequestURL="https://djemam.com/epsi/stores.json"
        val request = Request.Builder()
            .url(mRequestURL)
            .get()
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                val data = response.body?.string()

                if(data!=null){
                    val jsStores = JSONObject(data)
                    val bundle = Bundle()
                    bundle.putString("stores", jsStores.toString())
                    val fragInfo = MapsFragment()
                    fragInfo.setArguments(bundle)
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.fragment_container, fragInfo);
                    fragmentTransaction.commit();
                }
            }

        })*/
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setReorderingAllowed(true)
        fragmentTransaction.addToBackStack("Tab3Fragment") // name can be null
        fragmentTransaction.replace(R.id.fragment_container, MapsFragment::class.java, null)
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount>1)
            super.onBackPressed()
        else
            finish()
    }
}