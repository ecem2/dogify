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
import com.melo.dogify.databinding.FragmentCarefulWithTheseBinding
import com.melo.dogify.databinding.FragmentHarmfulOnesBinding
import com.melo.dogify.databinding.FragmentSafeOnesBinding
import com.melo.dogify.extensions.navigate
import com.melo.dogify.fragment.AppleFragment
import com.melo.dogify.model.FoodDescriptionModel
import com.melo.dogify.model.FoodModel
import com.melo.dogify.viewmodel.SoundsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class CarefulWithTheseFragment : BaseFragment<SoundsViewModel, FragmentCarefulWithTheseBinding>(),
    SafeOnesAdapter.ItemClickListener {

    private val safeOnesAdapter: SafeOnesAdapter by lazy {
        SafeOnesAdapter(
            requireContext(),
            this@CarefulWithTheseFragment
        )
    }

    override fun viewModelClass() = SoundsViewModel::class.java

    override fun getResourceLayoutId() = R.layout.fragment_careful_with_these

    override fun onInitDataBinding() {
        setupFoodStyle()
    }

    private fun setupFoodStyle() {
        viewBinding.rvCarefulOnes.apply {
            adapter = safeOnesAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
            setHasFixedSize(true)
        }
        safeOnesAdapter.submitList(viewModel.carefulOnesList)
    }


    override fun onItemClick(item: FoodModel) {
        val foodDescription = when (item) {
            viewModel.carefulOnesList.getOrNull(0) -> FoodDescriptionModel(
                image = R.drawable.cheese_blurry,
                titleText = R.string.cheese,
                text = R.string.cheese_article
            )

            viewModel.carefulOnesList.getOrNull(1) -> FoodDescriptionModel(
                image = R.drawable.cherries_blurry,
                titleText = R.string.cherries,
                text = R.string.cherries_article
            )

            viewModel.carefulOnesList.getOrNull(2) -> FoodDescriptionModel(
                image = R.drawable.egg_blurry,
                titleText = R.string.egg,
                text = R.string.egg_article
            )

            viewModel.carefulOnesList.getOrNull(3) -> FoodDescriptionModel(
                image = R.drawable.honey_blurry,
                titleText = R.string.honey,
                text = R.string.honey_article

            )

            viewModel.carefulOnesList.getOrNull(4) -> FoodDescriptionModel(
                image = R.drawable.ice_cream_blurry,
                titleText = R.string.ice_cream,
                text = R.string.ice_cream_article

            )

            viewModel.carefulOnesList.getOrNull(5) -> FoodDescriptionModel(
                image = R.drawable.juice_blurry,
                titleText = R.string.juice,
                text = R.string.juice_article


            )

            viewModel.carefulOnesList.getOrNull(6) -> FoodDescriptionModel(
                image = R.drawable.milk_blurry,
                titleText = R.string.milk,
                text = R.string.milk_article

            )

            viewModel.carefulOnesList.getOrNull(7) -> FoodDescriptionModel(
                image = R.drawable.peanut_blurry,
                titleText = R.string.peanut,
                text = R.string.peanut_article


            )

            viewModel.carefulOnesList.getOrNull(8) -> FoodDescriptionModel(
                image = R.drawable.pomegranate_blurry,
                titleText = R.string.pomegranate,
                text = R.string.pomegranate_article


            )

            viewModel.carefulOnesList.getOrNull(9) -> FoodDescriptionModel(
                image = R.drawable.tomato_blurry,
                titleText = R.string.tomato,
                text = R.string.tomato_article

            )

            viewModel.carefulOnesList.getOrNull(10) -> FoodDescriptionModel(
                image = R.drawable.yogurt_blurry,
                titleText = R.string.yogurt,
                text = R.string.yogurt_article


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

