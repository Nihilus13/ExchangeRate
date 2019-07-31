package com.nihilus13.exchange.rate.repository

import com.nihilus13.exchange.rate.model.RatesModel
import com.nihilus13.exchange.rate.model.Rate
import com.nihilus13.exchange.rate.model.RateConstans

interface DataTransformer {
    fun transform(ratesModel: RatesModel): List<Rate>
}

class DefaultDataTransformer : DataTransformer {
    override fun transform(ratesModel: RatesModel) = ratesModel.let {
        it.rates
            .filter { it.key == RateConstans.USD || it.key == RateConstans.PLN }
            .map { Rate(it.key, it.value.toString()) }
    }
}