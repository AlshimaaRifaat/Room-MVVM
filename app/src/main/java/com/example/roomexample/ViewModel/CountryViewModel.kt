package com.example.roomexample.ViewModel

//import com.example.task.model.PopularPeopleModel
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.roomexample.Model.CountryModel
import com.example.roomexample.Retrofit.APIClient
import com.example.roomexample.RoomViewModelKotlinSampleApplication

import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.TimeUnit

import retrofit2.Callback
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Log
import com.example.roomexample.Retrofit.RestApi

class CountryViewModel : ViewModel(){
   // public var popularPeopleListMutableLiveData: MutableLiveData<List<CountryModel>>? = null
  //  private lateinit var context: Context

 val BASE_URL = "https://restcountries.eu/rest/v2/"
    private val TAG = "CountryViewModel"

     fun getAllCountryList()
            : LiveData<List<CountryModel>> {
        return RoomViewModelKotlinSampleApplication.database!!.countryDao().getAllCountries()
       // popularPeopleListMutableLiveData = MutableLiveData<List<CountryModel>>()
      //  this.context = context
      //  getCountriesFromAPIAndStore()

        //  return listProductsMutableLiveData
      //  return popularPeopleListMutableLiveData as MutableLiveData<List<CountryModel>>

    }

     fun getCountriesFromAPIAndStore( ) {
        val gson = Gson()
        val retrofit =  Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build()

        val restApi = retrofit.create<RestApi>(RestApi::class.java)

         restApi.getAllCountries().enqueue(object : Callback<List<CountryModel>>{

            override fun onFailure(call: Call<List<CountryModel>>?, t: Throwable?) {
                Log.e(TAG,"OOPS!! something went wrong..")
            }

            override fun onResponse(call: Call<List<CountryModel>>?, response: Response<List<CountryModel>>?) {

                Log.e(TAG,response!!.body().toString())
                when(response.code())
                {
                    200 ->{
                        Thread(Runnable {

                            RoomViewModelKotlinSampleApplication.database!!.countryDao().deleteAllCountries()
                            RoomViewModelKotlinSampleApplication.database!!.countryDao().insertAllCountries(response.body()!!)

                        }).start()
                    }
                }

            }
        })

    }



}