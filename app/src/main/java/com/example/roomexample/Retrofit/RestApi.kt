package com.example.roomexample.Retrofit

import com.example.roomexample.Model.CountryModel
import retrofit2.Call
import retrofit2.http.GET

interface RestApi {
    @GET("all")
    fun getAllCountries() : Call<List<CountryModel>>
}