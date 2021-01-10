package com.anujan.accenture_n

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.anujan.accenture_n.entity.CityNames
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter : CityNameAdapter
    var cityNames: ArrayList<CityNames> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cityNameRV.layoutManager = LinearLayoutManager(this)

        adapter = CityNameAdapter(this,cityNames)
        cityNameRV.adapter = adapter
        setCityNames()
    }
    fun setCityNames(){
        cityNames.add(CityNames("Australia",R.drawable.austraila))
        cityNames.add(CityNames("India",R.drawable.india))
        cityNames.add(CityNames("London",R.drawable.london))
        cityNames.add(CityNames("Malaysia",R.drawable.malaysia))
        cityNames.add(CityNames("Maldives",R.drawable.maldives))
        cityNames.add(CityNames("Myanmar",R.drawable.myanmar))
        cityNames.add(CityNames("Singapore",R.drawable.singapore))
        cityNames.add(CityNames("USA",R.drawable.usa))

        adapter.notifyDataSetChanged()
    }
}