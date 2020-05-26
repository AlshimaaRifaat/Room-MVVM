package com.example.roomexample.DB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomexample.Model.CountryModel
@Database(entities = [(CountryModel::class)], version = 1)
abstract class CountryDatabase : RoomDatabase(){

    abstract fun countryDao() : CountryDao
}