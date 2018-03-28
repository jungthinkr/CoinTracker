package com.example.aaronl.cointrack.Repository;

import com.example.aaronl.cointrack.Model.Coin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by aaronl on 3/23/18.
 */

public interface CoinMarketCapAPI {
    @GET("/v1/ticker/")
    Call<List<Coin>> getTracker(@Query("start") String start, @Query("limit") String limit);

    @GET("/v1/ticker/{id}")
    Call<Coin> getCoin(@Path("id") String id);
}
