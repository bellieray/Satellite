package com.ebelli.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ebelli.core.data.model.Satellite
import com.ebelli.dashboard.databinding.RowItemSatelliteBinding

class SatellitesAdapter :
    ListAdapter<Satellite, SatellitesAdapter.SatellitesViewHolder>(SatellitesDiffUtilCallback) {
    object SatellitesDiffUtilCallback : DiffUtil.ItemCallback<Satellite>() {
        override fun areItemsTheSame(oldItem: Satellite, newItem: Satellite) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Satellite, newItem: Satellite) =
            oldItem.id == newItem.id && oldItem.active == newItem.active
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : SatellitesViewHolder=
        SatellitesViewHolder(RowItemSatelliteBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: SatellitesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SatellitesViewHolder(private val binding: RowItemSatelliteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(satellite: Satellite)= with(binding){
            this.satellite = satellite
        }
    }
}