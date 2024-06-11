package com.example.myquiz

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myquiz.databinding.ItemThemeBinding

class ThemeAdapter(private val themes:List<Int>, private val clickListener: OnThemeClickListener ):RecyclerView.Adapter<ThemeAdapter.ThemeViewHolder>() {
    interface OnThemeClickListener{
        fun onThemeClick(view:View, position: Int)
    }
    inner class ThemeViewHolder(val binding:ItemThemeBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(theme:Int){
            DicionarioTemas.GetThemeIcon(theme)?.let { binding.themeIcon.setImageResource(it) }
            DicionarioTemas.GetThemeById(theme)?.let { binding.themeName.text = it }

            binding.root.setOnClickListener{
                clickListener.onThemeClick(it,adapterPosition)
            }
        }

        /*
        val themeIcon:ImageView = view.findViewById(R.id.theme_icon)
        val themeName:TextView = view.findViewById(R.id.theme_name)

         */
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeViewHolder {
        val binding = ItemThemeBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ThemeViewHolder(binding)
    }

    override fun getItemCount(): Int = themes.size

    override fun onBindViewHolder(holder: ThemeViewHolder, position: Int) {
        val theme = themes[position]
        themes[position].apply {
            holder.bind(this)
        }
        Log.d("Adapter", "bind:$position")
    }

}