package com.example.myquiz.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myquiz.DicionarioTemas
import com.example.myquiz.R
import com.example.myquiz.ThemeAdapter

class ThemeSelectorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme_selector)

        val themes = DicionarioTemas.GetThemes()

        var selectedTheme:String

        for(id in themes){
            DicionarioTemas.GetThemeById(id)?.let { Log.d("DEBUG", it) }
        }

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this,2)
        recyclerView.adapter = ThemeAdapter(themes, object : ThemeAdapter.OnThemeClickListener{
            override fun onThemeClick(view: View, position: Int) {
                selectedTheme = DicionarioTemas.GetThemeById(themes[position])!!
                Log.d("DEBUG", "Selected theme: $selectedTheme")
            }
        })
    }
}