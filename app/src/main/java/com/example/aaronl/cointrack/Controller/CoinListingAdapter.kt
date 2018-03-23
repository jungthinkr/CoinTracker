package com.example.aaronl.cointrack.Controller

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.aaronl.cointrack.Model.Coin
import com.example.aaronl.cointrack.R
import kotlinx.android.synthetic.main.viewholder_coin.view.*

/**
 * Created by aaronl on 3/23/18.
 */

class CoinListingAdapter(val listener: CoinOnItemClickListener) : RecyclerView.Adapter<CoinListingAdapter.CoinViewHolder>() {


    private var coinList: List<Coin>? = null;

    inner class CoinViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bind(coin: Coin) {
            itemView.viewholder_coin_display_name.text = coin.name
            itemView.viewholder_coin_symbol.text = coin.symbol
            itemView.viewholder_coin_price_usd.text = coin.priceUsd
            itemView.setOnClickListener({ listener.onCoinClick(coin) })
        }
    }

    interface CoinOnItemClickListener {
        fun onCoinClick(coin: Coin)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder? {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_coin, parent, false)
        return CoinViewHolder(v)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        coinList?.let {
            holder.bind(it[position])
        }
    }

    override fun getItemCount(): Int {
        return coinList?.size ?: 0
    }

    fun injectCoinData(coinData: List<Coin>) {
        coinList = coinData
        notifyDataSetChanged()
    }
}
