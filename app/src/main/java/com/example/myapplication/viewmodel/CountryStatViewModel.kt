package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.Repository

class CountryStatViewModel(
    private val repository: Repository
): ViewModel() {
    fun getCountryStat(slug: String) = repository.getCountryStat(slug)
}