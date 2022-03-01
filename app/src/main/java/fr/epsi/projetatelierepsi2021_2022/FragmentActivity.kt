package fr.epsi.projetatelierepsi2021_2022

import android.os.Bundle
import android.view.View
import android.widget.TextView

class FragmentActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

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