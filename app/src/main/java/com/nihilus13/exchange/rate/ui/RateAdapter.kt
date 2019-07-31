package com.nihilus13.exchange.rate.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nihilus13.exchange.rate.R
import com.nihilus13.exchange.rate.databinding.RecyclerRateItemBinding
import com.nihilus13.exchange.rate.model.Rate

class RateAdapter : RecyclerView.Adapter<RateAdapter.RateViewHolder>() {

    private var ratesList: MutableList<Rate> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder {
        val binding: RecyclerRateItemBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.recycler_rate_item, parent, false)
        return RateViewHolder(binding)
    }

    override fun getItemCount(): Int = ratesList.size

    override fun onBindViewHolder(holder: RateViewHolder, position: Int) {
        ratesList[position].let {
            holder.binding.rate = it
        }
    }

    fun setRates(rates: List<Rate>) {
        //Maximum simplified. It could be additionally performed Diff to update only specific fields
        with(ratesList) {
            clear()
            addAll(rates)
        }
        notifyItemRangeChanged(0, rates.size)
    }

    class RateViewHolder(val binding: RecyclerRateItemBinding) : RecyclerView.ViewHolder(binding.root)
}