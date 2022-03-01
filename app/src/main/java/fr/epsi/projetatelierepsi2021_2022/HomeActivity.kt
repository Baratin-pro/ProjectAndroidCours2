package fr.epsi.projetatelierepsi2021_2022

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class HomeActivity : BaseActivity() {
    val tab1Fragment=FragmentMagasins.newInstance("","")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        showPictureAccount()
        val Nom:TextView = findViewById(R.id.textFName)
        val Prenom:TextView = findViewById(R.id.textSName)
        val Carte:TextView = findViewById(R.id.textNumberCard)

        Nom.setText(readSharedPref("Nom"))
        Prenom.setText(readSharedPref("Prenom"))
        Carte.setText(readSharedPref("Carte"))

        val tabCarte=findViewById<TextView>(R.id.textViewCarte)
        val tabOffres=findViewById<TextView>(R.id.textViewOffres)
        val tabMagasin=findViewById<TextView>(R.id.textViewMagasins)
        showBack()

        tabOffres.setOnClickListener(View.OnClickListener {
            showTabOffres()
        })

        tabMagasin.setOnClickListener(View.OnClickListener {
            showTabMagasins()
        })

        tabCarte.setOnClickListener(View.OnClickListener {
            showTabCarte()
        })
    }

    fun readSharedPref(key:String):String{
        val sharedPreferences: SharedPreferences = getSharedPreferences("profil", Context.MODE_PRIVATE)
        return sharedPreferences.getString(key,"not found").toString()
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
}




