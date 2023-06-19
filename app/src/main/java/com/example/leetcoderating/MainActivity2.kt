package com.example.leetcoderating

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RelativeLayout
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
import kotlin.math.log

class MainActivity2 : AppCompatActivity() {


    lateinit var tabLayout: TabLayout
    lateinit var viewPager2: ViewPager2
    lateinit var adapter2:MyViewPagerAdapter2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        tabLayout = findViewById(R.id.tabs2)
        viewPager2 = findViewById(R.id.viewpage2)

        adapter2 = MyViewPagerAdapter2(supportFragmentManager,lifecycle)
        viewPager2.adapter = adapter2

        TabLayoutMediator(tabLayout,viewPager2){
            tab,position->when(position){
                0->{
                    tab.text = "Ranks"
                }
                1->{
                    tab.text = "Friends Ranks"
                }
            }
        }.attach()





    }


}