package fr.epsi.projetatelierepsi2021_2022


import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class AccountActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        setHeaderTitle("Compte")


        val saveEmail: TextView = findViewById(R.id.editEmail)
        val savePrenom: TextView = findViewById(R.id.editPrenom)
        val saveNom: TextView = findViewById(R.id.editNom)
        val saveAdresse: TextView = findViewById(R.id.editAddress)
        val saveCodePostal: TextView = findViewById(R.id.editCodePostal)
        val saveVille: TextView = findViewById(R.id.editVille)
        val buttonSubmit: Button = findViewById(R.id.buttonSubmitModif)
        showBack()

        saveNom.setText(readSharedPref("Nom"))
        savePrenom.setText(readSharedPref("Prenom"))
        saveEmail.setText(readSharedPref("Email"))
        saveAdresse.setText(readSharedPref("Adresse"))
        saveCodePostal.setText(readSharedPref("CodePostal"))
        saveVille.setText(readSharedPref("Ville"))

        buttonSubmit.setOnClickListener(View.OnClickListener {
            writeSharedPref("Prenom",savePrenom.text.toString())
            writeSharedPref("Nom",saveNom.text.toString())
            writeSharedPref("Email",saveEmail.text.toString())
            writeSharedPref("Adresse",saveAdresse.text.toString())
            writeSharedPref("CodePostal",saveCodePostal.text.toString())
            writeSharedPref("Ville",saveVille.text.toString())
            isAccount()
        })
    }
}