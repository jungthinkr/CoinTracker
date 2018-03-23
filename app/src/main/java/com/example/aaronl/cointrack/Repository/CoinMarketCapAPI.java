package com.example.aaronl.cointrack.Repository;

import com.example.aaronl.cointrack.Model.Coin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by aaronl on 3/23/18.
 */

public interface CoinMarketCapAPI {
    @GET("/v1/ticker/")
    Call<List<Coin>> getTracker();
}
