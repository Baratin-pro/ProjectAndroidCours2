package fr.epsi.projetatelierepsi2021_2022

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.epsi.projetatelierepsi2021_2022.OffreAdapter.ViewHolder

class OffreAdapter (val offres: ArrayList<Offre>): RecyclerView.Adapter<ViewHolder>() {

    class ViewHolder(view:View) :RecyclerView.ViewHolder(view){
        val textViewProduct = view.findViewById<TextView>(R.id.textViewNameProduct)
        val textViewDescription = view.findViewById<TextView>(R.id.textViewDescription)
        val imageViewOffre = view.findViewById<ImageView>(R.id.imageViewOffre)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.cell_offre, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val offre = offres.get(position)
        holder.textViewProduct.text= offre.name
        holder.textViewDescription.text= offre.description
        Picasso.get().load(offre.imgUrl).into(holder.imageViewOffre)
    }

    override fun getItemCount(): Int {
        return offres.size
    }
}