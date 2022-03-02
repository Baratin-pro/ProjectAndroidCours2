package fr.epsi.projetatelierepsi2021_2022

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentOffres.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentOffres : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val offres = arrayListOf<Offre>()
        var rootView = inflater.inflate(R.layout.fragment_offres, container, false)

        val recyclerView:RecyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerViewOffres)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
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
                Log.d("Failure", "***")
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
                    }
                    activity?.runOnUiThread(Runnable {
                        offreAdapter.notifyDataSetChanged()
                    })
                }
            }

        })


        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentOffres.
         */
        // TODO: Rename and change types and number of parameters


        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentOffres().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }

            }

    }

}