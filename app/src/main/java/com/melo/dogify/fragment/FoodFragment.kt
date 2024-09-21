package com.melo.dogify.fragment

import android.graphics.Color
import android.view.Gravity
import com.google.android.material.tabs.TabLayout
import com.melo.dogify.R
import com.melo.dogify.adapter.FoodAdapter
import com.melo.dogify.adapter.FoodViewPagerAdapter
import com.melo.dogify.core.fragments.BaseFragment
import com.melo.dogify.databinding.FragmentFoodBinding
import com.melo.dogify.databinding.TabItemBinding
import com.melo.dogify.model.FoodModel
import com.melo.dogify.viewmodel. SoundsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class FoodFragment : BaseFragment<SoundsViewModel, FragmentFoodBinding>() {

    override fun viewModelClass() = SoundsViewModel::class.java

    override fun getResourceLayoutId() = R.layout.fragment_food

    override fun onInitDataBinding() {
        foodViewPager()
    }

    private fun foodViewPager() {
        val viewPager = viewBinding.foodViewPager
        viewPager.adapter = FoodViewPagerAdapter(requireActivity().supportFragmentManager)
        viewPager.currentItem = 0

        val tabLayout = viewBinding.tabLayout
        tabLayout.setupWithViewPager(viewPager)

        val tabTitles = arrayOf("Safe ones", "Harmful ones", "Careful with these")

        for (i in tabTitles.indices) {
            val tab = tabLayout.getTabAt(i)
            if (tab != null) {
                val bindingTabItem = TabItemBinding.inflate(layoutInflater)
                bindingTabItem.tabTitle.text = tabTitles[i]
                tab.customView = bindingTabItem.root
            }
        }


        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val selectedTabBinding = TabItemBinding.bind(tab.customView!!)
                selectedTabBinding.tabTitle.setTextColor(Color.WHITE)
                selectedTabBinding.tabTitle.setBackgroundResource(R.drawable.bg_tab_layout)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val unselectedTabBinding = TabItemBinding.bind(tab.customView!!)
                unselectedTabBinding.tabTitle.setTextColor(Color.BLACK)
                unselectedTabBinding.tabTitle.setBackgroundResource(0)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })
    }
}
