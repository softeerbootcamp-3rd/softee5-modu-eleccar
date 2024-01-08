package com.example.modueleccar.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.modueleccar.data.Charger
import com.example.modueleccar.data.ChargerList
import com.example.modueleccar.databinding.ItemInfoPurchaseBinding

class ChargerAdapter(
    private val chargerList: ChargerList
) : RecyclerView.Adapter<ChargerAdapter.ChargerViewHolder>() {
    class ChargerViewHolder(private val binding: ItemInfoPurchaseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(charger: Charger) {
            binding.apply {
                tvAvailableTime.text = "${charger.startHour} - ${charger.endHour}"
                tvDistance.text = "여기서 ${charger.distance}km"
                tvPerHourPrice.text = "${charger.pricePerHour}원/kwh"
                tvStationTitle.text = charger.type
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChargerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemInfoPurchaseBinding.inflate(inflater, parent, false)
        return ChargerViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return chargerList.chargers.size
    }

    override fun onBindViewHolder(holder: ChargerViewHolder, position: Int) {
        holder.bind(chargerList.chargers[position])
    }
}