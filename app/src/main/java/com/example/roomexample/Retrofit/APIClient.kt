package com.example.roomexample.Retrofit

import com.example.roomexample.Constants
import com.google.gson.Gson
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import com.google.gson.GsonBuilder

import java.util.concurrent.TimeUnit


class APIClient {

    /*private var retrofit: Retrofit? = null

    init {
        val gson = Gson()
        retrofit = Retrofit.Builder().baseUrl(Constants.BAS_URL).addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    companion object {

        @Volatile
        private var mInstance: APIClient? = null


        fun getInstance()= mInstance?: synchronized(this) {
            mInstance?:APIClient().also { mInstance=it }

        }
    }
    val api: APIInterface
        get() = retrofit!!.create<APIInterface>(APIInterface::class.java)
*/
}