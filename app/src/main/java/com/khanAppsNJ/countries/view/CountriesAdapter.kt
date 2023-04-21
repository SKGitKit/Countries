package com.khanAppsNJ.countries.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.khanAppsNJ.countries.R
import com.khanAppsNJ.countries.model.Country

class CountriesAdapter(private val countries: List<Country>?) : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameAndRegion = view.findViewById<TextView>(R.id.CnameAndregion_tv)
        private val capital = view.findViewById<TextView>(R.id.Ccapital_tv)
        private val code = view.findViewById<TextView>(R.id.Ccode_tv)


        fun bind(country: Country) {
            nameAndRegion.text = "${country.name}, ${country.region}"
            capital.text = country.capital
            code.text = country.code
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countries!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(countries!![position])
    }
}
