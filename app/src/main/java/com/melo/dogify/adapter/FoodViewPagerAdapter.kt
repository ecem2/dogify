@file:Suppress("DEPRECATION")

package com.melo.dogify.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.melo.dogify.viewpagerfragment.CarefulWithTheseFragment
import com.melo.dogify.viewpagerfragment.HarmfulOnesFragment
import com.melo.dogify.viewpagerfragment.SafeOnesFragment

@Suppress("DEPRECATION")
class FoodViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                SafeOnesFragment()
            }
            1 -> {
                HarmfulOnesFragment()
            }
            2 -> {
                CarefulWithTheseFragment()
            }
            else -> {
                SafeOnesFragment()
            }
        }
    }
}