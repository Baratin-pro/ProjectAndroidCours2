package fr.epsi.projetatelierepsi2021_2022

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setHeaderTitle("Création de compte")
        val buttonForm:Button = findViewById(R.id.button_form)

        buttonForm.setOnClickListener {
            val newIntent = Intent(application,LoginActivity::class.java)
            startActivity(newIntent)
        }
    }
}