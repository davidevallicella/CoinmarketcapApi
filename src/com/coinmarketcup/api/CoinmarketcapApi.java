package com.coinmarketcup.api;

import com.coinmarketcup.models.Currency;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

/**
 * Created by Davide Vallicella on 01.08.2017.
 */

public interface CoinmarketcapApi {
    @GET("ticker/")
    Call<List<Currency>> getCurrencies();
}