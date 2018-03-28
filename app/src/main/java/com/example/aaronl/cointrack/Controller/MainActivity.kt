package com.example.aaronl.cointrack

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.aaronl.cointrack.Controller.CoinDetailsActivity
import com.example.aaronl.cointrack.Controller.CoinListingAdapter
import com.example.aaronl.cointrack.Model.Coin
import com.example.aaronl.cointrack.Tools.CoinLinearLayoutManager
import com.example.aaronl.cointrack.ViewModel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(),
        CoinListingAdapter.CoinAdapterController,
        SwipeRefreshLayout.OnRefreshListener {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private val coinListingAdapter: CoinListingAdapter by lazy { CoinListingAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        initRecyclerView()
        onRefresh()
    }

    override fun onCoinClick(coin: Coin) {
        val intent = Intent(this, CoinDetailsActivity::class.java)
        intent.putExtra(Coin.COIN_PARCELABLE_KEY, coin)
        startActivity(intent)
    }

    override fun didReachBottom() {
        mainActivityViewModel.retrieveMoreCoinList().observe(this, Observer {
            if (it != null) {
                coinListingAdapter.injectCoinData(it)
            } else {
                Snackbar.make(findViewById(android.R.id.content), "Cannot get information", Snackbar.LENGTH_LONG)
            }
        })
    }

    override fun onRefresh() {
        coinListingAdapter.clear()
        mainActivityViewModel.retrieveInitialCoinList().observe(this, Observer {
            main_swipe_refresh_layout.isRefreshing = false
            if (it != null) {
                coinListingAdapter.injectCoinData(it)
            } else {
                Snackbar.make(findViewById(android.R.id.content), "Cannot get information", Snackbar.LENGTH_LONG)
            }
        })
    }


    private fun initRecyclerView() {
        main_recycler_view.layoutManager = CoinLinearLayoutManager(this)
        main_recycler_view.adapter = coinListingAdapter
        main_swipe_refresh_layout.setOnRefreshListener(this)
    }



}