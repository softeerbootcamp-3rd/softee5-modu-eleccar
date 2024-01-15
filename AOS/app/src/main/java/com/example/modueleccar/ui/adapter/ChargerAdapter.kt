package com.example.modueleccar.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.modueleccar.common.convertTimeString
import com.example.modueleccar.data.Charger
import com.example.modueleccar.data.ChargerList
import com.example.modueleccar.databinding.RvItemInfoPurchaseBinding

class ChargerAdapter(
    private val chargerList: ChargerList,
    private val navigationCallback: (chargeInfoId: Int, distance: Double) -> Unit
) : RecyclerView.Adapter<ChargerAdapter.ChargerViewHolder>() {
    inner class ChargerViewHolder(private val binding: RvItemInfoPurchaseBinding) :

        RecyclerView.ViewHolder(binding.root) {
        fun bind(charger: Charger) {
            binding.apply {
                root.setOnClickListener {
                    navigationCallback(charger.chargerInfoId, charger.distance)
                }
                tvElecAmount.text = "${charger.speed.toString()}kW"
                tvStationTitle.text = charger.title
                tvAvailableTime.text = convertTimeString(charger.startHour, charger.endHour)
                tvDistance.text = "내 위치에서 " + String.format("%.1f", charger.distance) + "km"
                tvPerHourPrice.text = "${charger.pricePerHour / charger.speed}원/kwh"

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChargerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = RvItemInfoPurchaseBinding.inflate(inflater, parent, false)
        return ChargerViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return chargerList.chargers.size
    }

    override fun onBindViewHolder(holder: ChargerViewHolder, position: Int) {
        holder.bind(chargerList.chargers[position])
    }
}