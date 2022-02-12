package fr.epsi.projetatelierepsi2021_2022

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
        val buttonSubmit:Button = findViewById(R.id.buttonSubmitForm)


    }
}