package com.example.myapplication.data.api

import com.example.myapplication.data.model.Country
import com.example.myapplication.data.model.CountryStat
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("countries")
    fun getCountries(): Call<List<Country>>

    @GET("country/{slug}")
    fun getCountryStat(@Path("slug") slug: String): Call<List<CountryStat>>
}