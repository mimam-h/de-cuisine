package com.example.final_project_psi

import android.content.ClipData.Item
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    companion object{
        const val CAT = "category"
        const val RESEP = "resep"
        const val CREATOR = "creator"
        const val REVIEW = "ingridients"
        const val RATING = "rate"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()


        val creator = intent.getStringExtra(CREATOR)
        val cat = intent.getStringExtra(CAT)
        val review = intent.getStringExtra(REVIEW)
        val rate = intent.getDoubleExtra(RATING,0.0)
        val resep = intent.getStringExtra(RESEP)


        setCurrentFragment(firstFragment)
        var navigation_menu = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navigation_menu.setOnItemSelectedListener {
            /* Belum Berhasil */
            val bundle = Bundle()
            bundle.putString(SecondFragment.CREATOR,creator)
            bundle.putString(SecondFragment.REVIEW,review)
            bundle.putString(SecondFragment.CAT,cat)
            bundle.putDouble(SecondFragment.RATING,rate)
            bundle.putString(SecondFragment.RESEP,resep)
            secondFragment.arguments = bundle
            when(it.itemId){
                R.id.miHome -> setCurrentFragment(firstFragment)
                R.id.miArticle -> setCurrentFragment(secondFragment)
            }
            true
        }
    }

    override fun <T : View?> findViewById(id: Int): T {
        return super.findViewById(id)
    }
    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, fragment)
            commit()
        }
}