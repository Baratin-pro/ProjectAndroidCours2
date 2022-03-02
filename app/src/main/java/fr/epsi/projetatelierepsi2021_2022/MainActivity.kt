package fr.epsi.projetatelierepsi2021_2022

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setHeaderTitle("Création de compte")
        isAccount()
        val buttonForm:Button = findViewById(R.id.button_form)

        buttonForm.setOnClickListener {
            val newIntent = Intent(application,LoginActivity::class.java)
            startActivity(newIntent)
        }

        val buttonQrCode:ImageButton = findViewById(R.id.button_qr_code)

        buttonQrCode.setOnClickListener {
            val newIntent = Intent(application,QrcodeActivity::class.java)
            startActivity(newIntent)
        }
    }
}