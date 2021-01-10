package com.anujan.accenture_n

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.anujan.accenture_n.api.NetworkClient
import com.anujan.accenture_n.api.RequestInterface
import com.anujan.accenture_n.entity.CityWeatherMain
import com.anujan.accenture_n.room.AppDatabase
import com.anujan.accenture_n.room.entity.CityRoom
import com.anujan.accenture_n.room.entity.WeatherDetails
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_weather_of_city.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class WeatherOfCityActivity : AppCompatActivity() {


    private lateinit var adapter : WeatherAdapter
    var weatherList: ArrayList<WeatherDetails> = ArrayList()
    private  var cityName:String? = null
    var cityRoom: CityRoom? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_of_city)

        val intent = intent
        cityName = intent.getStringExtra("CityName")
      //  cityNameWeather.text = cityName

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = cityName+ " Forecast"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)

        weatherRV.layoutManager = LinearLayoutManager(this)

        val cm = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo

        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting) {
            cityName?.let { getWeather(it) }
        }
        else{
            getDataFromRoom(cityName!!,false)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    private fun getWeather(cityNameC: String){
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "WeatherDatabase"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
        val cityDao = db.cityDao()
        cityDao.deleteAllCity(cityNameC)
        val weatherDao = db.weatherDao()
        weatherDao.deleteAll(cityNameC)

        weatherList.clear()
        val request = NetworkClient.buildService(RequestInterface::class.java)
        val call = request.getWeather(getString(R.string.APIKEY),cityNameC)

        call.enqueue(object : Callback<CityWeatherMain>{
            override fun onResponse(call: Call<CityWeatherMain>, response: Response<CityWeatherMain>) {
                if (response.isSuccessful){
                    cityDao.insertAll(CityRoom(
                        cityNameC,
                        stringtoDate(response.body()?.city?.sunrise!!.toLong()),
                        stringtoDate(response.body()?.city?.sunset!!.toLong())
                    ))
                    for (item in response.body()!!.list) {
                        weatherDao.insertAll(
                            WeatherDetails(
                                cityNameC,
                                item.dt_txt,
                                String.format("%.2f", (item.main.temp - 273.15)),
                                item.weather[0].description,
                                item.wind.speed.toString(),
                                item.wind.deg.toString(),
                                item.clouds.all.toString(),
                                item.visibility.toString(),
                                String.format("%.2f", (item.main.temp_min - 273.15)),
                                String.format("%.2f", (item.main.temp_max - 273.15)),
                                String.format("%.2f", (item.main.feels_like - 273.15)),
                                item.main.pressure.toString(),
                                item.main.humidity.toString(),
                                item.main.temp_kf.toString(),
                                item.main.sea_level.toString(),
                                item.main.grnd_level.toString()
                            )
                        )
                    }
                    getDataFromRoom(cityNameC,true)
                }
            }
            override fun onFailure(call: Call<CityWeatherMain>, t: Throwable) {
                Toast.makeText(this@WeatherOfCityActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getDataFromRoom(cityNameC: String,internet:Boolean){
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "WeatherDatabase"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
        val cityDao = db.cityDao()
        val weatherDao = db.weatherDao()
        cityRoom = cityDao.loadAllByIds(cityNameC)
        if (cityRoom != null){
            if (!internet){
                dialogBox(
                    "Your internet is off so this data is not updated, This data when you came last time with internet loaded data",
                    false
                )
            }
            sunriseAt.text = cityRoom!!.sunrise
            sunsetAt.text = cityRoom!!.sunset
        }
        else{
            dialogBox("No data so you have to on your internet connection please check your connection!",true)
        }
        weatherList = weatherDao.loadAllByIds(cityNameC) as ArrayList<WeatherDetails>
        if (weatherList.size !=0){
            adapter = WeatherAdapter(this, weatherList)
            weatherRV.adapter = adapter
        }
    }
    fun stringtoDate(dates: Long): String {
        val sdf = java.text.SimpleDateFormat("yyyy MMM dd  HH:mm:ss")
        val date = java.util.Date(dates * 1000)
        return sdf.format(date)
    }
    fun dialogBox(message:String,finish:Boolean){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("No Internet")
        builder.setMessage(message)
        builder.setPositiveButton("Ok") { dialog, which ->
            if(finish){
                finish()
            }
            else{
                dialog.dismiss()
            }
        }
        builder.show()
    }
}

