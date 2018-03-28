package com.example.aaronl.cointrack.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

import com.example.aaronl.cointrack.Model.Coin
import com.example.aaronl.cointrack.Repository.CoinMarketCapAPIRepository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by aaronl on 3/23/18.
 */

class MainActivityViewModel : ViewModel() {
    var page = 0

    fun retrieveInitialCoinList(): LiveData<List<Coin>> {
        page = 0
        return retrieveCoinsFromAPI(page)
    }

    fun retrieveMoreCoinList(): LiveData<List<Coin>> {
        page++
        return retrieveCoinsFromAPI(page)
    }

    private fun retrieveCoinsFromAPI(page: Int): LiveData<List<Coin>>  {
        val mutableCoinLiveData = MutableLiveData<List<Coin>>()
        CoinMarketCapAPIRepository.getSharedInstance().getCoins(object : Callback<List<Coin>> {
            override fun onResponse(call: Call<List<Coin>>, response: Response<List<Coin>>) {
                mutableCoinLiveData.value = response.body()
            }

            override fun onFailure(call: Call<List<Coin>>, t: Throwable) {
                mutableCoinLiveData.value = null
            }
        }, page)
        return mutableCoinLiveData
    }
}
