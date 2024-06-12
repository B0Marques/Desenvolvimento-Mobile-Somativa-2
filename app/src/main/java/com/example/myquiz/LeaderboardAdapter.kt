package com.example.myquiz

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myquiz.databinding.LeaderboardInfoBinding
import com.example.myquiz.model.Player

class LeaderboardAdapter(private val usuarios: List<Player>)  :RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>(){




    inner class ViewHolder(val binding:LeaderboardInfoBinding):RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(player: Player, counter:Int) {
            binding.userPlace.text = "${counter}º place: "
            binding.userName.text = player.nome
            binding.userScore.text = player.pontos.toString()
            //binding.userTheme.text = DicionarioTemas.GetThemeById(player.tema)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LeaderboardInfoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return usuarios.size
    }
    var counter = 1
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        usuarios[position].apply {
            holder.bind(this, counter)
            counter++
        }
    }
}