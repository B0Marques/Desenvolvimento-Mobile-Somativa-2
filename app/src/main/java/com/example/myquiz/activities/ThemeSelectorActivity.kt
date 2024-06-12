package com.example.myquiz.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myquiz.DicionarioTemas
import com.example.myquiz.R
import com.example.myquiz.ThemeAdapter
import com.example.myquiz.model.LeaderBordRepository
import javax.inject.Inject

class ThemeSelectorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme_selector)

        val themes = DicionarioTemas.GetThemes()

        var selectedTheme:String

        val selection = intent.getIntExtra("select_value",0)




        for(id in themes){
            DicionarioTemas.GetThemeById(id)?.let { Log.d("DEBUG", it) }
        }

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this,2)
        /*
        recyclerView.adapter = ThemeAdapter(themes, object : ThemeAdapter.OnThemeClickListener{
            override fun onThemeClick(view: View, position: Int) {
                selectedTheme = DicionarioTemas.GetThemeById(themes[position])!!
                changeActivity(selection,themes[position])
                Log.d("DEBUG", "Selected theme: $selectedTheme")
            }
        })

         */
        recyclerView.adapter =ThemeAdapter(themes,object : ThemeAdapter.OnThemeClickListener{
            override fun onThemeClick(view: View, position: Int) {
                /*
                val themeSelected:Int = themes[position]
                val intent = Intent(this@ThemeSelectorActivity, QuestionActivity::class.java)

                intent.putExtra("theme",themeSelected)
                startActivity(intent)

                 */
                changeActivity(selection,themes[position])

            }

        })

    }
    @SuppressLint("SuspiciousIndentation")
    fun changeActivity(selection:Int, theme:Int){
        /*
        val newActivity = Intent(this,QuestionActivity::class.java)
        newActivity.putExtra("theme",theme)
        startActivity(newActivity)

         */
        Log.d("DEBUG", "${selection}")

        if(selection==0){
        val newActivity = Intent(this,QuestionActivity::class.java)
            newActivity.putExtra("theme",theme)
            startActivity(newActivity)
        }
        else{
            val newActivity = Intent(this,Leaderboard_Activity::class.java)
            newActivity.putExtra("theme",theme)
            startActivity(newActivity)
        }


    }

    override fun onResume() {
        super.onResume()
    }
}