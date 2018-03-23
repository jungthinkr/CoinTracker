package com.example.aaronl.cointrack.Repository;

import com.example.aaronl.cointrack.Model.Coin;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


/**
 * Created by aaronl on 3/23/18.
 */

public class CoinMarketCapAPIRepository {
    private static CoinMarketCapAPIRepository sharedInstance;

    public static CoinMarketCapAPIRepository getSharedInstance() {
        if (sharedInstance == null) {
            sharedInstance = new CoinMarketCapAPIRepository();
        }
        return sharedInstance;
    }

    public void getCoins(Callback<List<Coin>> cb) {
        Call<List<Coin>> coinCall = HttpClient.getCoinMarketAPI().getTracker();
        coinCall.enqueue(cb);
    }

}
