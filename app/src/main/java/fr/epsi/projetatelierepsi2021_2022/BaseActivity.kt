package fr.epsi.projetatelierepsi2021_2022

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.io.File

open class BaseActivity : AppCompatActivity() {
    fun showBack(){
        val imageViewBack = findViewById<ImageView>(R.id.imageViewBack)
        imageViewBack.visibility= View.VISIBLE
        imageViewBack.setOnClickListener(View.OnClickListener {
            finish()
        })
    }

    fun showPictureAccount(){
        val imageViewBack2:ImageView = findViewById<ImageView>(R.id.imageView2)
        imageViewBack2.visibility= View.VISIBLE
    }

    fun getAccount(){
        val imageProfile = findViewById<ImageView>(R.id.imageView2)
        imageProfile.visibility= View.VISIBLE
        imageProfile.setOnClickListener(View.OnClickListener {
            val newIntent = Intent(application,AccountActivity::class.java)
            startActivity(newIntent)
        })
    }

    fun showToast(txt : String){
        Toast.makeText(this,txt, Toast.LENGTH_SHORT).show()
    }

    fun setHeaderTitle(text:String){
        val textViewTitle = findViewById<TextView>(R.id.textViewTitle)
        textViewTitle.text=text
    }

    fun writeSharedPref(key:String,value:String){
        val sharedPreferences: SharedPreferences = getSharedPreferences("profil", Context.MODE_PRIVATE)
        val editor =sharedPreferences.edit()
        editor.putString(key,value)
        editor.apply()
    }

    fun readSharedPref(key:String):String{
        val sharedPreferences: SharedPreferences = getSharedPreferences("profil", Context.MODE_PRIVATE)
        return sharedPreferences.getString(key,"not found").toString()
    }

    fun isAccount() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("profil", Context.MODE_PRIVATE)
        val path = "/data/data/fr.epsi.projetatelierepsi2021_2022/shared_prefs"
        val file = File("$path/profil.xml")

        if(file.exists()){
            val newIntent = Intent(application,FragmentActivity::class.java)
            startActivity(newIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Epsi","############ onCreate ###########"+ javaClass.simpleName)
    }


    override fun onStart() {
        super.onStart()
        Log.d("Epsi","############ onStart ###########"+ javaClass.simpleName)
    }

    override fun onResume() {
        super.onResume()
        Log.d("Epsi","############ onResume ###########"+ javaClass.simpleName)
    }

    override fun onPause() {
        super.onPause()
        Log.d("Epsi","############ onPause ###########"+ javaClass.simpleName)
    }

    override fun onStop() {
        super.onStop()
        Log.d("Epsi","############ onStop ###########"+ javaClass.simpleName)
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("Epsi","############ onDestroy ###########"+ javaClass.simpleName)
    }
}