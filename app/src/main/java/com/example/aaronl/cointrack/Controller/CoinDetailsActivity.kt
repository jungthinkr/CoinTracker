package com.example.aaronl.cointrack.Controller
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import com.example.aaronl.cointrack.Model.Coin
import com.example.aaronl.cointrack.R
import kotlinx.android.synthetic.main.activity_coin_detail.*

/**
 * Created by aaronl on 3/23/18.
 */

class CoinDetailsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        val bundle = intent.extras
        val coin: Coin = bundle.getParcelable(Coin.COIN_PARCELABLE_KEY)
        supportActionBar?.title = coin.symbol + " - " + coin.name
        setUpUIFromCoin(coin)
    }

    private fun setUpUIFromCoin(coin: Coin) {
        activity_coin_detail_display_name.text = coin.name
        activity_coin_detail_symbol.text = coin.symbol
        activity_coin_detail_rank.text = coin.rank
        activity_coin_detail_usd_price.text = coin.priceUsd



//        private var name: String? = null
//
//        private var symbol: String? = null
//
//        private var rank: String? = null
//
//        private var priceUsd: String? = null
//
//        private var priceBtc: String? = null
//
//        private var _24hVolumeUsd: String? = null
//
//        private var marketCapUsd: String? = null
//
//        private var availableSupply: String? = null
//
//        private var totalSupply: String? = null
//
//        private var percentChange1h: String? = null
//
//        private var percentChange24h: String? = null
//
//        private var percentChange7d: String? = null
//
//        private var lastUpdated: String? = null
    }

}
