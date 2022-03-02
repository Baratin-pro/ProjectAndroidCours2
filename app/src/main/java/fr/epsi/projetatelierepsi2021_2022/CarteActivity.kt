package fr.epsi.projetatelierepsi2021_2022

import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment


class CarteActivity : Fragment() {
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.activity_home, container, false).apply{
        super.onCreate(savedInstanceState)

        val Nom:TextView = findViewById(R.id.textFName)
        val Prenom:TextView = findViewById(R.id.textSName)
        val Carte:TextView = findViewById(R.id.textNumberCard)

        Nom.setText(readSharedPref("Nom"))
        Prenom.setText(readSharedPref("Prenom"))
        Carte.setText(readSharedPref("Carte"))

    }

    fun readSharedPref(key:String):String{
        val sharedPreferences: SharedPreferences = this.requireActivity().getSharedPreferences("profil", Context.MODE_PRIVATE)
        return sharedPreferences.getString(key,"not found").toString()
    }
}