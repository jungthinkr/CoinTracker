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
    private static int PAGE_THRESHOLD = 100;

    public static CoinMarketCapAPIRepository getSharedInstance() {
        if (sharedInstance == null) {
            sharedInstance = new CoinMarketCapAPIRepository();
        }
        return sharedInstance;
    }

    public void getCoins(Callback<List<Coin>> cb, int page) {
        Call<List<Coin>> coinCall = HttpClient.getCoinMarketAPI().getTracker(String.valueOf(page*PAGE_THRESHOLD), String.valueOf(PAGE_THRESHOLD));
        coinCall.enqueue(cb);
    }

    public void getSingleCoin(Callback<Coin> cb, String coinId){
        Call<Coin> coinCall = HttpClient.getCoinMarketAPI().getCoin(coinId);
        coinCall.enqueue(cb);
    }

}
