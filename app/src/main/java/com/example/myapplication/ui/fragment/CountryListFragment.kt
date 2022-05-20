package com.example.myapplication.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.data.Repository
import com.example.myapplication.databinding.FragmentCountryListBinding
import com.example.myapplication.ui.adapter.CountryAdapter
import com.example.myapplication.viewmodel.CountryViewModel

class CountryListFragment : Fragment(R.layout.fragment_country_list) {
    private lateinit var binding: FragmentCountryListBinding
    private lateinit var countryViewModel: CountryViewModel
    private lateinit var CountriesAdapter: CountryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCountryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        observe()
    }

    fun initUI() {
        countryViewModel = CountryViewModel(Repository.getInstance())
        CountriesAdapter = CountryAdapter(countryViewModel)
        binding.Countries.apply {
            adapter = CountriesAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    fun observe() {
        countryViewModel.getCountries().observe(viewLifecycleOwner) {countries ->
            if (countries != null) {
                CountriesAdapter.countries = countries
                CountriesAdapter.notifyDataSetChanged()
            }
        }
    }
}