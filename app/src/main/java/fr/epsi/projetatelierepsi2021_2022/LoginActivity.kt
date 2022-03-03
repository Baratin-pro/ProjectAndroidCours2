package fr.epsi.projetatelierepsi2021_2022

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setHeaderTitle("Création de compte")
        showBack()

        val savePrenom:EditText= findViewById(R.id.editPrenom)
        val saveNom:EditText= findViewById(R.id.editNom)
        val saveEmail:EditText= findViewById(R.id.editEmail)
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
        val textFirstName:TextView = findViewById(R.id.editPrenom)
        val textLastName:TextView = findViewById(R.id.editNom)
        val textEmail:TextView = findViewById(R.id.editEmail)
        val textAdress:TextView = findViewById(R.id.editAddress)
        val textZipcode:TextView = findViewById(R.id.editCodePostal)
        val textCity:TextView = findViewById(R.id.editVille)
        val textCardRef:TextView = findViewById(R.id.editCarte)

        textFirstName.text = intent.getStringExtra("firstName")
        textLastName.text = intent.getStringExtra("lastName")
        textEmail.text = intent.getStringExtra("email")
        textAdress.text = intent.getStringExtra("address")
        textZipcode.text = intent.getStringExtra("zipcode")
        textCity.text = intent.getStringExtra("city")
        textCardRef.text = intent.getStringExtra("cardRef")

    }
}
