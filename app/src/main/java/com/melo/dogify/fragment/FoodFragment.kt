package com.melo.dogify.fragment

import android.graphics.Color
import com.google.android.material.tabs.TabLayout
import com.melo.dogify.R
import com.melo.dogify.adapter.FoodViewPagerAdapter
import com.melo.dogify.core.fragments.BaseFragment
import com.melo.dogify.databinding.FragmentFoodBinding
import com.melo.dogify.databinding.TabItemBinding
import com.melo.dogify.viewmodel.SoundsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class FoodFragment : BaseFragment<SoundsViewModel, FragmentFoodBinding>() {

    override fun viewModelClass() = SoundsViewModel::class.java

    override fun getResourceLayoutId() = R.layout.fragment_food

    override fun onInitDataBinding() {
        foodViewPager()
        viewBinding.foodBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun foodViewPager() {
        val viewPager = viewBinding.foodViewPager
        viewPager.adapter = FoodViewPagerAdapter(childFragmentManager)
        viewPager.currentItem = 0

        val tabLayout = viewBinding.tabLayout
        tabLayout.setupWithViewPager(viewPager)

        val tabTitles = arrayOf(
            getString(R.string.safe_ones),
            getString(R.string.harmful_ones),
            getString(R.string.careful_with_these)
        )
        for (i in tabTitles.indices) {
            val tab = tabLayout.getTabAt(i)
            if (tab != null) {
                val bindingTabItem = TabItemBinding.inflate(layoutInflater)
                bindingTabItem.tabTitle.text = tabTitles[i]
                tab.customView = bindingTabItem.root
            }
        }

        val firstTab = tabLayout.getTabAt(0)
        if (firstTab != null) {
            val selectedTabBinding = TabItemBinding.bind(firstTab.customView!!)
            selectedTabBinding.tabTitle.setTextColor(Color.WHITE)
            selectedTabBinding.tabTitle.setBackgroundResource(R.drawable.bg_tab_layout)
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