package com.nihilus13.exchange.rate.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nihilus13.exchange.rate.R
import com.nihilus13.exchange.rate.databinding.FragmentRatesBinding
import com.nihilus13.exchange.rate.model.Rate
import com.nihilus13.exchange.rate.viewmodel.RateViewModel

class RatesFragment : Fragment() {

    private lateinit var recyclerRateBinding: FragmentRatesBinding

    private lateinit var adapterRate: RateAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        recyclerRateBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_rates, container, false)
        adapterRate = RateAdapter()
        recyclerRateBinding.recyclerRates.adapter = adapterRate
        recyclerRateBinding.recyclerRates.setHasFixedSize(true)
        recyclerRateBinding.recyclerRates.layoutManager = LinearLayoutManager(activity)
            .apply {
                orientation = RecyclerView.VERTICAL
            }

        return recyclerRateBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val rateViewModel = ViewModelProviders.of(this).get(RateViewModel::class.java)
        subscribeUi(rateViewModel.getRates())
    }

    private fun subscribeUi(ratesLiveData: LiveData<List<Rate>>) {
        ratesLiveData.observe(this,
            Observer<List<Rate>> {
                if (it == null) {
                    recyclerRateBinding.isLoading = true
                } else {
                    recyclerRateBinding.isLoading = false
                    adapterRate.setRates(it)
                }

                recyclerRateBinding.executePendingBindings()
            })
    }
}