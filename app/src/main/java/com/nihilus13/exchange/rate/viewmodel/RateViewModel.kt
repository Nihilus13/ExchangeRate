package com.nihilus13.exchange.rate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.nihilus13.exchange.rate.model.Rate
import com.nihilus13.exchange.rate.repository.RatesRepository

class RateViewModel : ViewModel() {

    private val ratesRepository = RatesRepository()
        .apply { startFetchingData() }

    private val observableRates = MediatorLiveData<List<Rate>>()
        .apply {
            value = null
            addSource(ratesRepository.getRates(), ::setValue)
        }

    fun getRates(): LiveData<List<Rate>> = observableRates
}