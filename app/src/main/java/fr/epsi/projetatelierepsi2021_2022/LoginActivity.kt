package fr.epsi.projetatelierepsi2021_2022

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setHeaderTitle("Création de compte")
        showBack()

        val saveEmail:EditText= findViewById(R.id.editEmail)
        val savePrenom:EditText= findViewById(R.id.editPrenom)
        val saveNom:EditText= findViewById(R.id.editNom)
        val saveAdresse:EditText= findViewById(R.id.editAddress)
        val saveCodePostal:EditText= findViewById(R.id.editCodePostal)
        val saveVille:EditText= findViewById(R.id.editVille)
        val saveCarte:EditText= findViewById(R.id.editCarte)
        val buttonSubmit:Button = findViewById(R.id.buttonSubmitForm)
        showBack()
        buttonSubmit.setOnClickListener(View.OnClickListener {
            writeSharedPref("Prenom",savePrenom.text.toString())
            writeSharedPref("Nom",saveNom.text.toString())
            writeSharedPref("Email",saveEmail.text.toString())
            writeSharedPref("Adresse",saveAdresse.text.toString())
            writeSharedPref("CodePostal",saveCodePostal.text.toString())
            writeSharedPref("Ville",saveVille.text.toString())
            writeSharedPref("Carte",saveCarte.text.toString())
            isAccount()
        })

        /*saveEmail.setText(readSharedPref("email"))
        savePrenom.setText(readSharedPref("prenom"))
        saveNom.setText(readSharedPref("nom")) */
    }



    fun writeSharedPref(key:String,value:String){
        val sharedPreferences: SharedPreferences = getSharedPreferences("profil", Context.MODE_PRIVATE)
        val editor =sharedPreferences.edit()
        editor.putString(key,value)
        editor.apply()
    }


}
