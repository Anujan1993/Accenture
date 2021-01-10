package com.anujan.accenture_n

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.anujan.accenture_n.entity.CityNames


class CityNameAdapter(
    val context: Context,
    private val list: List<CityNames>
): RecyclerView.Adapter<CityNameViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityNameViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.city_row, parent, false)
        return CityNameViewHolder(view)
    }
    override fun onBindViewHolder(holder: CityNameViewHolder, position: Int) {
        holder.cityName!!.text = list[position].cityName
        holder.cityImage!!.setImageResource(list[position].cityImageID)

        val displayMetrics = context.resources.displayMetrics
        val pxWidth = displayMetrics.widthPixels
        val height = (pxWidth*0.6)

        val params = holder.cityImage!!.layoutParams
        params.width = pxWidth
        params.height = height.toInt()
        holder.cityImage!!.layoutParams = params

        holder.cardView!!.setOnClickListener {
            val intent = Intent(context, WeatherOfCityActivity::class.java)
            intent.putExtra("CityName", list[position].cityName)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class CityNameViewHolder(itemView: View):
    RecyclerView.ViewHolder(itemView) {

    var cardView: CardView? = null
    var cityImage: ImageView? = null
    var cityName: TextView? = null

    init {
        cardView = itemView.findViewById(R.id.cityNameCard)
        cityImage = itemView.findViewById(R.id.cityImage)
        cityName = itemView.findViewById(R.id.cityName)

    }

}
