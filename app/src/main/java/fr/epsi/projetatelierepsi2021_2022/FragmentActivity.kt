package fr.epsi.projetatelierepsi2021_2022

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView

class FragmentActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("fragment", "***")
        setContentView(R.layout.activity_fragmet)
        //showPictureAccount()

        val tabCarte=findViewById<TextView>(R.id.textViewCarte)
        val tabOffres=findViewById<TextView>(R.id.textViewOffres)
        val tabMagasin=findViewById<TextView>(R.id.textViewMagasins)
        showBack()

        tabOffres.setOnClickListener(View.OnClickListener{
            showTabOffres()
        })

        tabMagasin.setOnClickListener(View.OnClickListener {
            showTabMagasins()
        })

        tabCarte.setOnClickListener(View.OnClickListener {
            showTabCarte()
        })

        showTabCarte()
    }

    private fun showTabCarte() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setReorderingAllowed(true)
        fragmentTransaction.addToBackStack("Tab1Fragment") // name can be null
        fragmentTransaction.replace(R.id.fragment_container, CarteActivity::class.java, null)
        fragmentTransaction.commit()
    }

    private fun showTabOffres() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setReorderingAllowed(true)
        fragmentTransaction.addToBackStack("Tab2Fragment") // name can be null
        fragmentTransaction.replace(R.id.fragment_container, FragmentOffres::class.java, null)
        fragmentTransaction.commit()
    }

    private fun showTabMagasins() {
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

    fun readSharedPref(key:String):String{
        val sharedPreferences: SharedPreferences = getSharedPreferences("profil", Context.MODE_PRIVATE)
        return sharedPreferences.getString(key,"not found").toString()
    }
}