package com.example.myapplication.data

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.api.ApiClient
import com.example.myapplication.data.model.Country
import com.example.myapplication.data.model.CountryStat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository private constructor() {
    companion object {
        private var instance: Repository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: Repository()
        }
    }

    fun getCountries(): LiveData<List<Country>> {
        val result = MutableLiveData<List<Country>>()

        val call = ApiClient.getApiService()?.getCountries()
        call?.enqueue(object : Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                result.value = if (response.isSuccessful) response.body() else emptyList()
            }

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }
        })

        return result
    }

    fun getCountryStat(slug: String): LiveData<List<CountryStat>> {
        val result = MutableLiveData<List<CountryStat>>()

        val call = ApiClient.getApiService()?.getCountryStat(slug)
        call?.enqueue(object : Callback<List<CountryStat>> {
            override fun onResponse(call: Call<List<CountryStat>>, response: Response<List<CountryStat>>) {
                result.value = if (response.isSuccessful) response.body() else emptyList()
            }

            override fun onFailure(call: Call<List<CountryStat>>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }
        })

        return result
    }
}