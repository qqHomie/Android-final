package com.example.myapplication.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.data.Repository
import com.example.myapplication.data.model.CountryStat
import com.example.myapplication.databinding.FragmentCountryListBinding
import com.example.myapplication.databinding.FragmentCountryStatBinding
import com.example.myapplication.ui.adapter.CountryAdapter
import com.example.myapplication.viewmodel.CountryStatViewModel
import com.example.myapplication.viewmodel.CountryViewModel

class CountryStatFragment : Fragment(R.layout.fragment_country_stat) {
    private lateinit var binding: FragmentCountryStatBinding
    private lateinit var countryStatViewModel: CountryStatViewModel

    private var countryStat: CountryStat? = null

    companion object {
        const val ARG_SLUG = "arg_slug"

        fun newInstance(slug: String): CountryStatFragment {
            val args = bundleOf((ARG_SLUG to slug))
            val fragment = CountryStatFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCountryStatBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        observe()
    }

    @SuppressLint("SetTextI18n")
    fun initUI() {
        countryStatViewModel = CountryStatViewModel(Repository.getInstance())
        binding.apply {
            if (countryStat != null) {
                tvCountryName.text = countryStat?.Country
                tvConfirmedCnt.text = "Confirmed: ${countryStat?.Confirmed}"
                tvDeathCnt.text = "Deaths: ${countryStat?.Deaths}"
                tvRecoverCnt.text = "Recovered: ${countryStat?.Recovered}"
                tvActiveCnt.text = "Active: ${countryStat?.Active}"
            } else {
                tvCountryName.text = countryStat?.Country
                tvConfirmedCnt.text = "Confirmed: 0"
                tvDeathCnt.text = "Deaths: 0"
                tvRecoverCnt.text = "Recovered: 0"
                tvActiveCnt.text = "Active: 0"
            }

        }
    }

    fun observe() {
        countryStatViewModel.getCountryStat(requireArguments().getString(ARG_SLUG)!!)
            .observe(viewLifecycleOwner) { countryStats ->
            if (countryStats.isNotEmpty()) {
                countryStat = countryStats[countryStats.lastIndex]
                initUI()
            }
        }
    }
}