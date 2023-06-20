package com.example.leetcoderating.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.leetcoderating.R

class AboutMeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_about_me, container, false)

        val imageView = view.findViewById<ImageView>(R.id.image_view)
        imageView.setOnClickListener {
            openGitHubProfile()
        }

        return view
    }

    private fun openGitHubProfile() {
        val url = "https://github.com/furiousluck/LeetCodeRating/releases"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}