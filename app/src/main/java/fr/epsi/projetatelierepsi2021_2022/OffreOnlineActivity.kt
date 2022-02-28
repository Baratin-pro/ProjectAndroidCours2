package fr.epsi.projetatelierepsi2021_2022

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class OffreOnlineActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_offres)
        setHeaderTitle("Offres")
        showBack()

        val offres = arrayListOf<Offre>()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewOffres)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val offreAdapter = OffreAdapter(offres)
        recyclerView.adapter=offreAdapter

        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        val mRequestURL="https://djemam.com/epsi/offers.json"
        val request = Request.Builder()
            .url(mRequestURL)
            .get()
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                val data = response.body?.string()

                if(data!=null){
                    val jsOffres = JSONObject(data)
                    val jsArrayOffres= jsOffres.getJSONArray("items")
                    for(i in 0 until jsArrayOffres.length()){
                        val jsOffre = jsArrayOffres.getJSONObject(i)
                        val offre = Offre(jsOffre.optString("name",""),
                            jsOffre.optString("description",""),
                            jsOffre.optString("picture_url",""))
                        offres.add(offre)
                        Log.d("offre",offre.name)
                    }
                    Log.d("Offres","${offres.size}")
                    runOnUiThread(Runnable {
                        offreAdapter.notifyDataSetChanged()
                    })
                }
            }

        })

    }
}