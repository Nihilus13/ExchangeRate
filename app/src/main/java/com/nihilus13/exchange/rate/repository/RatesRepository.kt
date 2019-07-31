package com.nihilus13.exchange.rate.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.nihilus13.exchange.rate.model.Rate
import java.io.IOException
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class RatesRepository internal constructor(
    private val webService: WebService = ExchangeRateWebService(),
    private val threadPoolExecutor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor(),
    private val observableRates: MediatorLiveData<List<Rate>> = MediatorLiveData(),
    private val dataTransformer: DataTransformer = DefaultDataTransformer()
) {
    //Using RxJava can be overkill in this purpose. Simple single thread executor with okhttp call is far enough.
    private val runnableFetchRates = Runnable {
        try {
            val ratesModel = webService.fetchRates()

            val rateList = dataTransformer.transform(ratesModel)

            observableRates.postValue(rateList)
        } catch (exception: IOException) {
            Log.e("RATES REPOSITORY", exception.localizedMessage)
        }

    }

    fun startFetchingData() =
        threadPoolExecutor.scheduleAtFixedRate(runnableFetchRates, 0, 1, TimeUnit.MINUTES)

    fun getRates(): LiveData<List<Rate>> = observableRates

}