package com.example.myquiz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myquiz.databinding.ItemThemeBinding
import com.example.myquiz.model.Player

class LeaderboardAdapter(private val usuarios: List<Player>)  :RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>(){




    inner class ViewHolder(val binding:ItemThemeBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(player: Player) {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemThemeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return usuarios.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        usuarios[position].apply {
            holder.bind(this)
        }
    }
}