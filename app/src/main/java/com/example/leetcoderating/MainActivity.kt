package com.example.leetcoderating

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TableLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager2: ViewPager2
    lateinit var adapter1: MyViewPagerAdapter1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //new fragment
        tabLayout = findViewById(R.id.tabs1)
        viewPager2 = findViewById(R.id.viewpage1)

        adapter1 = MyViewPagerAdapter1(supportFragmentManager,lifecycle)
        viewPager2.adapter = adapter1

        TabLayoutMediator(tabLayout,viewPager2){
            tab,position->when(position){
                0->{
                    tab.text = "Home"
                }
                1->{
                    tab.text = "Friends"
                }
                2->{
                    tab.text = "About Me"
                }
            }
        }.attach()
    }
}