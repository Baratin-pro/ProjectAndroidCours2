package fr.epsi.projetatelierepsi2021_2022

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class HomeActivity : BaseActivity() {
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

        val buttonForm:Button = findViewById(R.id.buttonTest)

        buttonForm.setOnClickListener {
            val newIntent = Intent(application,FragmentActivity::class.java)
            startActivity(newIntent)
        }

    }

    fun readSharedPref(key:String):String{
        val sharedPreferences: SharedPreferences = getSharedPreferences("profil", Context.MODE_PRIVATE)
        return sharedPreferences.getString(key,"not found").toString()
    }


}




