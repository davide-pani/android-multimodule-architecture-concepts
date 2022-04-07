package com.davidepani.data.api

import com.davidepani.data.models.CoinApiResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface CoinGeckoApiService {

    @GET("/api/v3/coins/markets")
    suspend fun getCoinsMarkets(@Query("vs_currency") currency: String = "usd"): List<CoinApiResponse>


    companion object {
        const val BASE_URL = "https://api.coingecko.com"
    }

}