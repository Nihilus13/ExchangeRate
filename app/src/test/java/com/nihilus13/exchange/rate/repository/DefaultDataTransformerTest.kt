package com.nihilus13.exchange.rate.repository

import com.nihilus13.exchange.rate.model.RatesModel
import org.junit.Test

import org.junit.Assert.*

class DefaultDataTransformerTest {
    private val tested = DefaultDataTransformer()

    @Test
    fun transformsCorrectlyToRatesWithPLN() {
        val model = RatesModel(mapOf("PLN" to 666f), "USD", "")

        val result = tested.transform(model)

        assertTrue(result.first().currencyName == "PLN")
        assertTrue(result.first().currencyRate == "666.0")
    }

    @Test
    fun transformsCorrectlyToRatesWithUSD() {
        val model = RatesModel(mapOf("USD" to 666f), "USD", "")

        val result = tested.transform(model)

        assertTrue(result.first().currencyName == "USD")
        assertTrue(result.first().currencyRate == "666.0")
    }

    @Test
    fun doesNotTransformCorrectlyToRatesWithHUF() {
        val model = RatesModel(mapOf("HUF" to 666f), "USD", "")

        val result = tested.transform(model)

        assertTrue(result.isEmpty())
    }
}