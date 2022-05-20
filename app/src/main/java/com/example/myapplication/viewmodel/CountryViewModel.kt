package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.Repository

class CountryViewModel(
    private val repository: Repository
): ViewModel() {
    fun getCountries() = repository.getCountries()
}