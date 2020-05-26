package com.example.roomexample

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.roomexample.Model.CountryModel
import com.example.roomexample.ViewModel.CountryViewModel
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {

    lateinit var countryViewModel:CountryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        countryViewModel =
            ViewModelProvider(this)[CountryViewModel::class.java]
        if(isNetworkConnected(this))
        {
            countryViewModel.getCountriesFromAPIAndStore()

        }
        else
        {
            Toast.makeText(this,"No internet found. Showing cached list in the view", Toast.LENGTH_LONG).show()
        }
        CountryList()
    }
    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
    private fun CountryList() {
        countryViewModel.getAllCountryList().observe(this,
            Observer<List<CountryModel>> { countryModel ->
                if (countryModel != null) {
                    val  countryRecyclerViewAdapter = CountryRecyclerViewAdapter(this,countryModel)
                    // listAdapter.onClickItemLatestProduct(this@MainActivity)
                    countryRecyclerView.layoutManager = LinearLayoutManager(this)
                    countryRecyclerView.adapter=countryRecyclerViewAdapter

                }
            })
    }
}
