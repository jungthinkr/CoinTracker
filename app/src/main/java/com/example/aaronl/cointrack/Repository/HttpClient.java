package com.example.aaronl.cointrack.Repository;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aaronl on 3/23/18.
 */

public class HttpClient {
    private static final String baseURL = "https://api.coinmarketcap.com";
    private static Retrofit sharedInstance;
    static public Retrofit getSharedInstance() {
        if (sharedInstance == null) {
            sharedInstance = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sharedInstance;
    }

    static public CoinMarketCapAPI getCoinMarketAPI() {
       return HttpClient.getSharedInstance().create(CoinMarketCapAPI.class);
    }
}
