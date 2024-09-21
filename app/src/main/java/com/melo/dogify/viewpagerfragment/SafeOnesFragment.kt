package com.melo.dogify.viewpagerfragment

import androidx.recyclerview.widget.GridLayoutManager
import com.melo.dogify.R
import com.melo.dogify.adapter.SafeOnesAdapter
import com.melo.dogify.core.fragments.BaseFragment
import com.melo.dogify.databinding.FragmentSafeOnesBinding
import com.melo.dogify.model.FoodModel
import com.melo.dogify.viewmodel.SoundsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class SafeOnesFragment : BaseFragment<SoundsViewModel, FragmentSafeOnesBinding>(),
    SafeOnesAdapter.ItemClickListener {

    private val safeOnesAdapter: SafeOnesAdapter by lazy { SafeOnesAdapter(requireContext(), this@SafeOnesFragment) }

    override fun viewModelClass() = SoundsViewModel::class.java

    override fun getResourceLayoutId() = R.layout.fragment_safe_ones

    override fun onInitDataBinding() {
        setupFoodStyle()
    }

    private fun setupFoodStyle() {
        viewBinding.rvSafeOnes.apply {
            adapter = safeOnesAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
            setHasFixedSize(true)
        }
        safeOnesAdapter.submitList(viewModel.foodCardList)
    }

    override fun onItemClick(item: FoodModel) {

    }
}