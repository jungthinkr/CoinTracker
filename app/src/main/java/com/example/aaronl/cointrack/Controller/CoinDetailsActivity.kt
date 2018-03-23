package com.example.aaronl.cointrack.Controller
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.aaronl.cointrack.Model.Coin

/**
 * Created by aaronl on 3/23/18.
 */

class CoinDetailsActivity: AppCompatActivity() {

    private lateinit var coin: Coin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        val coin: Coin = bundle.getParcelable(Coin.COIN_PARCELABLE_KEY)
        this.coin = coin
    }
}
