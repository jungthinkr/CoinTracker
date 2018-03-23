package com.example.aaronl.cointrack

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.example.aaronl.cointrack.Controller.CoinDetailsActivity
import com.example.aaronl.cointrack.Controller.CoinListingAdapter
import com.example.aaronl.cointrack.Model.Coin
import com.example.aaronl.cointrack.ViewModel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, CoinListingAdapter.CoinOnItemClickListener {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private val coinListingAdapter: CoinListingAdapter by lazy { CoinListingAdapter(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        initRecyclerView()
        collectCoinData()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun initRecyclerView() {
        main_recycler_view.layoutManager = LinearLayoutManager(this)
        main_recycler_view.adapter = coinListingAdapter
    }

    private fun collectCoinData() {
        mainActivityViewModel.retrieveCoinsFromAPI().observe(this, Observer {
            if (it != null) {
                coinListingAdapter.injectCoinData(it)
            }
        })
    }

    override fun onCoinClick(coin: Coin) {
        val intent = Intent(this, CoinDetailsActivity::class.java)
        intent.putExtra(Coin.COIN_PARCELABLE_KEY, coin)
        startActivity(intent)
    }
}