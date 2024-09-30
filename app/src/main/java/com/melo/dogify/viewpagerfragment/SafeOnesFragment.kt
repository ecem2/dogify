package com.melo.dogify.viewpagerfragment

import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.melo.dogify.R
import com.melo.dogify.adapter.SafeOnesAdapter
import com.melo.dogify.core.fragments.BaseFragment
import com.melo.dogify.databinding.FragmentSafeOnesBinding
import com.melo.dogify.extensions.navigate
import com.melo.dogify.fragment.AppleFragment
import com.melo.dogify.model.FoodDescriptionModel
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
        val foodDescription = when (item) {
            viewModel.foodCardList.getOrNull(0) -> FoodDescriptionModel(
                image = R.drawable.apple_blurry,
                titleText = R.string.apple,
                text = R.string.apple_article
            )
            viewModel.foodCardList.getOrNull(1) -> FoodDescriptionModel(
                image = R.drawable.banana_blurry,
                titleText = R.string.banana,
                text = R.string.banana_article
            )
            viewModel.foodCardList.getOrNull(2) -> FoodDescriptionModel(
                image = R.drawable.bluberry_blurry,
                titleText = R.string.blueberry,
                text = R.string.blueberry_article
            )
            viewModel.foodCardList.getOrNull(3) -> FoodDescriptionModel(
                image = R.drawable.broccoli_blurry,
                titleText = R.string.broccoli,
                text = R.string.broccoli_article

            )
            viewModel.foodCardList.getOrNull(4) -> FoodDescriptionModel(
                image = R.drawable.carrot_blurry,
                titleText = R.string.carrot,
                text = R.string.carrot_article

            )
            viewModel.foodCardList.getOrNull(5) -> FoodDescriptionModel(
                image = R.drawable.beet_blurry,
                titleText = R.string.beet,
                text = R.string.beet_article


            )
            viewModel.foodCardList.getOrNull(6) -> FoodDescriptionModel(
                image = R.drawable.cucumber_blurry,
                titleText = R.string.cucumber,
                text = R.string.cucumber_article

            )
            viewModel.foodCardList.getOrNull(7) -> FoodDescriptionModel(
                image = R.drawable.mango_blurry,
                titleText = R.string.mango,
                text = R.string.mango_article


            )
            viewModel.foodCardList.getOrNull(8) -> FoodDescriptionModel(
                image = R.drawable.green_peas_blurry,
                titleText = R.string.green_peas,
                text = R.string.green_peas_article


            )
            viewModel.foodCardList.getOrNull(9) -> FoodDescriptionModel(
                image = R.drawable.orange_blurry,
                titleText = R.string.orange,
                text = R.string.orange_article

            )
            viewModel.foodCardList.getOrNull(10) -> FoodDescriptionModel(
                image = R.drawable.lettuce_blurry,
                titleText = R.string.lettuce,
                text = R.string.lettuce_article


            )
            viewModel.foodCardList.getOrNull(11) -> FoodDescriptionModel(
                image = R.drawable.strawberry_blurry,
                titleText = R.string.strawberry,
                text = R.string.strawberry_article


            )
            viewModel.foodCardList.getOrNull(12) -> FoodDescriptionModel(
                image = R.drawable.pineapple_blurry,
                titleText = R.string.pineapple,
                text = R.string.pineapple_article

            )
            viewModel.foodCardList.getOrNull(13) -> FoodDescriptionModel(
                image = R.drawable.watermelon_blurry,
                titleText = R.string.watermelon,
                text = R.string.watermelon_article


            )
            viewModel.foodCardList.getOrNull(14) -> FoodDescriptionModel(
                image = R.drawable.parsley_blurry,
                titleText = R.string.parsley,
                text = R.string.parsley_article


            )
            viewModel.foodCardList.getOrNull(15) -> FoodDescriptionModel(
                image = R.drawable.turnip_blurry,
                titleText = R.string.turnip,
                text = R.string.turnip_article


            )
            viewModel.foodCardList.getOrNull(16) -> FoodDescriptionModel(
                image = R.drawable.spinach_blurry,
                titleText = R.string.spinach,
                text = R.string.spinach_article

            )
            viewModel.foodCardList.getOrNull(17) -> FoodDescriptionModel(
                image = R.drawable.bread_blurry,
                titleText = R.string.bread,
                text = R.string.bread_article


            )

            else -> null
        }

        foodDescription?.let {
            val bundle = Bundle().apply {
                putParcelable("foodDescription", it)
            }

            val appleFragment = AppleFragment().apply {
                arguments = bundle
            }

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, appleFragment)
                .addToBackStack(null)
                .commit()

        }
    }

}

