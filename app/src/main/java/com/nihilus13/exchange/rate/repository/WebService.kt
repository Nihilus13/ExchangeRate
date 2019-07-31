package com.nihilus13.exchange.rate.repository

import com.google.gson.Gson
import com.nihilus13.exchange.rate.model.RatesModel
import okhttp3.OkHttpClient
import okhttp3.Request

interface WebService {
    fun fetchRates(): RatesModel
}

class ExchangeRateWebService internal constructor(
    private val client: OkHttpClient = OkHttpClient(),
    private val gson: Gson = Gson()
) : WebService {

    override fun fetchRates(): RatesModel {
        val request = Request.Builder()
            .url("https://api.exchangeratesapi.io/latest")
            .build()

        val response = client.newCall(request).execute()

        return gson.fromJson(response.body?.charStream(), RatesModel::class.java)
    }
}