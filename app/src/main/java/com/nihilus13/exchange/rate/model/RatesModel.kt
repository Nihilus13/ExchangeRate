package com.nihilus13.exchange.rate.model

typealias Rates = Map<String, Float>

data class RatesModel(
    val rates: Rates,
    val base: String,
    val date: String
)

data class Rate(val currencyName: String, val currencyRate: String)

object RateConstans {
    const val PLN = "PLN"
    const val USD = "USD"
}