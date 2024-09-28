package com.melo.dogify.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.melo.dogify.viewpagerfragment.CarefulWithTheseFragment
import com.melo.dogify.viewpagerfragment.HarmfulOnesFragment
import com.melo.dogify.viewpagerfragment.SafeOnesFragment

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
               throw IllegalArgumentException("Invalid position")

            }
        }
    }
}

//class FoodViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
//
//    override fun getItemCount() = 3
//
//    override fun createFragment(position: Int): Fragment {
//        return when (position) {
//            0 -> {
//                SafeOnesFragment()
//            }
//            1 -> {
//                HarmfulOnesFragment()
//            }
//            2 -> {
//                CarefulWithTheseFragment()
//            }
//            else -> {
//                SafeOnesFragment()
//            }
//        }
//    }
//}