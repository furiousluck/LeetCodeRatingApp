package com.example.leetcoderating

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.leetcoderating.fragments.AboutMeFragment
import com.example.leetcoderating.fragments.FriendsFragment
import com.example.leetcoderating.fragments.HomeFragment

class MyViewPagerAdapter1(fragmentManager: FragmentManager, lifecycle: Lifecycle):FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{
                HomeFragment()
            }
            1->{
                FriendsFragment()
            }
            2->{
                AboutMeFragment()
            }
            else->{
                HomeFragment()
            }
        }
    }


}