package fr.epsi.projetatelierepsi2021_2022

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            val newIntent= Intent(application,MainActivity::class.java)
            startActivity(newIntent)
            finish()
        },1500)
    }
}