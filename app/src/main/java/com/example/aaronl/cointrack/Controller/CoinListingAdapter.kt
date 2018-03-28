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

class CoinListingAdapter(val listener: CoinAdapterController) : RecyclerView.Adapter<CoinListingAdapter.CoinViewHolder>() {

    private var coinList: ArrayList<Coin> = ArrayList();

    inner class CoinViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bind(coin: Coin) {
            itemView.viewholder_coin_ranking.text = coin.rank + "."
            itemView.viewholder_coin_display_name.text = coin.name
            itemView.viewholder_coin_symbol.text = coin.symbol
            itemView.viewholder_coin_price_usd.text = "$" + coin.priceUsd
            itemView.setOnClickListener({ listener.onCoinClick(coin) })
        }
    }

    interface CoinAdapterController {
        fun onCoinClick(coin: Coin)
        fun didReachBottom()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder? {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_coin, parent, false)
        return CoinViewHolder(v)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {

        holder.bind(coinList[position])
        if (position == coinList.lastIndex) {
            listener.didReachBottom()
        }

    }

    override fun getItemCount(): Int {
        return coinList.size
    }

    fun injectCoinData(coinData: List<Coin>) {
        coinList.addAll(coinData)
        notifyDataSetChanged()
    }

    fun clear() {
        val coinListSize = coinList.size
        coinList.clear()
        notifyItemRangeChanged(0, coinListSize)
    }
}
