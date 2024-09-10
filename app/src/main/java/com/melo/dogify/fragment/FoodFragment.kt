package com.melo.dogify.fragment


import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.melo.dogify.R
import com.melo.dogify.adapter.FoodAdapter
import com.melo.dogify.adapter.FoodViewPagerAdapter
import com.melo.dogify.core.fragments.BaseFragment
import com.melo.dogify.databinding.FragmentFoodBinding
import com.melo.dogify.model.FoodModel
import com.melo.dogify.viewmodel. SoundsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint

class FoodFragment : BaseFragment<SoundsViewModel, FragmentFoodBinding>(),
    FoodAdapter.ItemClickListener {

    private val foodAdapter: FoodAdapter by lazy {
        FoodAdapter(
            requireContext(),
            this@FoodFragment

        )
    }

    private var selectedCard: FoodModel? = null
    private var selectedItemPosition: Int = 0


    override fun viewModelClass() = SoundsViewModel::class.java

    override fun getResourceLayoutId() = R.layout.fragment_food

    override fun onInitDataBinding() {
        foodViewPager()
    }


    override fun onResume() {
        super.onResume()
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
                val textView = TextView(context)
                textView.text = tabTitles[i]
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                textView.typeface = ResourcesCompat.getFont(requireContext(), R.font.tt_firs_neue_trial_demibold)
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)

                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                textView.maxLines = 1

                val padding = resources.getDimensionPixelSize(R.dimen.tab_padding)
                textView.setPadding(padding, padding, padding, padding)

                textView.gravity = Gravity.CENTER

                val params = ViewGroup.MarginLayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT)
                params.width = ViewGroup.LayoutParams.MATCH_PARENT
                textView.layoutParams = params
                textView.minimumWidth = 100

                tab.customView = textView
            }
        }

        for (i in 0 until tabLayout.tabCount) {
            val tab = (tabLayout.getChildAt(0) as ViewGroup).getChildAt(i)
            val layoutParams = tab.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.marginEnd = 10
            layoutParams.marginStart = 10
            tab.requestLayout()
        }

        val firstTab = tabLayout.getTabAt(0)
        firstTab?.customView?.setBackgroundResource(R.drawable.bg_tablayout)
        firstTab?.select()

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                val selectedTab = tabLayout.getTabAt(position)
                selectedTab?.customView?.setBackgroundResource(R.drawable.bg_tablayout)

                for (i in 0 until tabLayout.tabCount) {
                    if (i != position) {
                        tabLayout.getTabAt(i)?.customView?.background = null
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }
    override fun onItemClick(item: FoodModel) {
        selectedItemPosition = viewModel.foodCardList.indexOf(item)
        selectedCard = item
        foodAdapter.notifyDataSetChanged()
    }
}
