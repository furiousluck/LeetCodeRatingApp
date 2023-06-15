package com.example.leetcoderating

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val intent = intent
        val contestid = intent.getStringExtra("contestId")
        val txt = findViewById<TextView>(R.id.textView)
        txt.text = contestid


    }
}