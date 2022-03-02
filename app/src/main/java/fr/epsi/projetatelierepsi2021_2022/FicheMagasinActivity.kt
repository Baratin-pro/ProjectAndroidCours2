package fr.epsi.projetatelierepsi2021_2022;

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class FicheMagasinActivity : BaseActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.magasin_fiche)

        val magasin_nom = intent.getStringExtra("magasin_nom")
        val image = intent.getStringExtra("image")
        val address = intent.getStringExtra("address")
        val zipcode = intent.getStringExtra("zipcode")
        val city = intent.getStringExtra("city")
        val description = intent.getStringExtra("description")

        val imageView = findViewById<ImageView>(R.id.imageMagasin)
        val textAdress=findViewById<TextView>(R.id.textAdress)
        val textzipcode=findViewById<TextView>(R.id.textzipcode)
        val textdescription=findViewById<TextView>(R.id.textdescription)

        showBack()

        if (magasin_nom != null) {
            setHeaderTitle(magasin_nom)
        }
        textAdress.setText(address)
        textzipcode.setText(zipcode + " - " + city)
        textdescription.setText("Description : " + description)
        Picasso.get().load(image).into(imageView)

    }
}
