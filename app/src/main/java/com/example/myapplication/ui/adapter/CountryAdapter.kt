package com.example.myapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.model.Country
import com.example.myapplication.databinding.CountryItemBinding
import com.example.myapplication.ui.fragment.CountryStatFragment
import com.example.myapplication.viewmodel.CountryViewModel

class CountryAdapter(
    private val countryViewModel: CountryViewModel
    ): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
        var countries: List<Country> = emptyList()

    inner class CountryViewHolder(
        private val binding: CountryItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country) {
            binding.apply {
                tvCountryName.text = country.Country
                btnViewStat.setOnClickListener {
                    val activity = it.context as AppCompatActivity
                    activity.supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.fragmentContainerView,
                            CountryStatFragment.newInstance(country.Slug),
                            null
                        )
                        .addToBackStack(null)
                        .commit()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = CountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countries[position]
        holder.bind(country)
    }

    override fun getItemCount() = countries.size
}