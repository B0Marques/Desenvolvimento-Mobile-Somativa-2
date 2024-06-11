package com.example.myquiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ThemeAdapter(private val themes:List<Int> ):RecyclerView.Adapter<ThemeAdapter.ThemeViewHolder>() {
    interface OnThemeClickListener{
        fun onThemeClick(view:View, position: Int)
    }
    inner class ThemeViewHolder(view:View):RecyclerView.ViewHolder(view){
        
        fun bind(){

        }


        val themeIcon:ImageView = view.findViewById(R.id.theme_icon)
        val themeName:TextView = view.findViewById(R.id.theme_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_theme, parent,false)
        return ThemeViewHolder(view)
    }

    override fun getItemCount(): Int = themes.size

    override fun onBindViewHolder(holder: ThemeViewHolder, position: Int) {
        val theme = themes[position]
        DicionarioTemas.GetThemeIcon(theme)?.let { holder.themeIcon.setImageResource(it) }
        holder.themeName.text = DicionarioTemas.GetThemeById(theme);
    }

}