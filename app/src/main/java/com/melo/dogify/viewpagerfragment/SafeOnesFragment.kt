package com.melo.dogify.viewpagerfragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.melo.dogify.R
import com.melo.dogify.adapter.ArticleAdapter
import com.melo.dogify.adapter.SafeOnesAdapter
import com.melo.dogify.adapter.SoundsAdapter
import com.melo.dogify.core.fragments.BaseFragment
import com.melo.dogify.databinding.FragmentArticleBinding
import com.melo.dogify.databinding.FragmentSafeOnesBinding
import com.melo.dogify.databinding.FragmentSoundsBinding
import com.melo.dogify.model.ArticleModel
import com.melo.dogify.model.CardModel
import com.melo.dogify.model.FoodModel
import com.melo.dogify.model.TrainingModel
import com.melo.dogify.viewmodel.SoundsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint

class SafeOnesFragment : BaseFragment<SoundsViewModel, FragmentSafeOnesBinding>(),
    SafeOnesAdapter.ItemClickListener {

    private val safeOnesAdapter: SafeOnesAdapter by lazy {
        SafeOnesAdapter(
            requireContext(),
            this@SafeOnesFragment

        )
    }

    private var selectedFood: FoodModel? = null
    private var selectedItemPosition: Int = 0


    override fun viewModelClass() = SoundsViewModel::class.java

    override fun getResourceLayoutId() = R.layout.fragment_safe_ones

    override fun onInitDataBinding() {
        setupFoodStyle()
    }

    private fun setupFoodStyle() {
        viewBinding.rvSafeOnes.apply {
            adapter = safeOnesAdapter
            setHasFixedSize(true)
        }
        safeOnesAdapter.submitList(viewModel.foodCardList)

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onItemClick(item: FoodModel) {
        selectedItemPosition = viewModel.foodCardList.indexOf(item)
        selectedFood = item
        safeOnesAdapter.notifyDataSetChanged()
    }
}