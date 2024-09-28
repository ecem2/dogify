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
import com.melo.dogify.databinding.FragmentHarmfulOnesBinding
import com.melo.dogify.databinding.FragmentSafeOnesBinding
import com.melo.dogify.extensions.navigate
import com.melo.dogify.fragment.AppleFragment
import com.melo.dogify.model.FoodDescriptionModel
import com.melo.dogify.model.FoodModel
import com.melo.dogify.viewmodel.SoundsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class HarmfulOnesFragment : BaseFragment<SoundsViewModel, FragmentHarmfulOnesBinding>(),
    SafeOnesAdapter.ItemClickListener {

    private val safeOnesAdapter: SafeOnesAdapter by lazy {
        SafeOnesAdapter(
            requireContext(),
            this@HarmfulOnesFragment
        )
    }

    override fun viewModelClass() = SoundsViewModel::class.java

    override fun getResourceLayoutId() = R.layout.fragment_harmful_ones

    override fun onInitDataBinding() {
        setupFoodStyle()
    }

    private fun setupFoodStyle() {
        viewBinding.rvHarmfulOnes.apply {
            adapter = safeOnesAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
            setHasFixedSize(true)
        }
        safeOnesAdapter.submitList(viewModel.harmfulOnesList)
    }


    override fun onItemClick(item: FoodModel) {
        val foodDescription = when (item) {
            viewModel.harmfulOnesList.getOrNull(0) -> FoodDescriptionModel(
                image = R.drawable.alcohol_blurry,
                titleText = R.string.alcohol,
                text = R.string.alcohol_article
            )

            viewModel.harmfulOnesList.getOrNull(1) -> FoodDescriptionModel(
                image = R.drawable.avocado_blurry,
                titleText = R.string.avocado,
                text = R.string.avocado_article
            )

            viewModel.harmfulOnesList.getOrNull(2) -> FoodDescriptionModel(
                image = R.drawable.candy_blurry,
                titleText = R.string.candy,
                text = R.string.candy_article
            )

            viewModel.harmfulOnesList.getOrNull(3) -> FoodDescriptionModel(
                image = R.drawable.chocolate_blurry,
                titleText = R.string.chocolate,
                text = R.string.chocolate_article

            )

            viewModel.harmfulOnesList.getOrNull(4) -> FoodDescriptionModel(
                image = R.drawable.coffee_blurry,
                titleText = R.string.coffee,
                text = R.string.coffee_article

            )

            viewModel.harmfulOnesList.getOrNull(5) -> FoodDescriptionModel(
                image = R.drawable.bones_blurry,
                titleText = R.string.bones,
                text = R.string.bones_article


            )

            viewModel.harmfulOnesList.getOrNull(6) -> FoodDescriptionModel(
                image = R.drawable.fishbones_blurry,
                titleText = R.string.fishbones,
                text = R.string.fishbones_article

            )

            viewModel.harmfulOnesList.getOrNull(7) -> FoodDescriptionModel(
                image = R.drawable.garlic_blurry,
                titleText = R.string.garlic,
                text = R.string.garlic_article


            )

            viewModel.harmfulOnesList.getOrNull(8) -> FoodDescriptionModel(
                image = R.drawable.grapes_blurry,
                titleText = R.string.grapes,
                text = R.string.grapes_article


            )

            viewModel.harmfulOnesList.getOrNull(9) -> FoodDescriptionModel(
                image = R.drawable.leek_blurry,
                titleText = R.string.leek,
                text = R.string.leek_article

            )

            viewModel.harmfulOnesList.getOrNull(10) -> FoodDescriptionModel(
                image = R.drawable.lemon_blurry,
                titleText = R.string.lemon,
                text = R.string.lemon_article


            )

            viewModel.harmfulOnesList.getOrNull(11) -> FoodDescriptionModel(
                image = R.drawable.mushroom_blurry,
                titleText = R.string.mushroom,
                text = R.string.mushroom_article


            )

            viewModel.harmfulOnesList.getOrNull(12) -> FoodDescriptionModel(
                image = R.drawable.onion_blurry,
                titleText = R.string.onion,
                text = R.string.onion_article

            )

            viewModel.harmfulOnesList.getOrNull(13) -> FoodDescriptionModel(
                image = R.drawable.tea_blurry,
                titleText = R.string.tea,
                text = R.string.tea_article


            )

            viewModel.harmfulOnesList.getOrNull(14) -> FoodDescriptionModel(
                image = R.drawable.tobacco_blurry,
                titleText = R.string.tobacco,
                text = R.string.tobacco_article


            )

            else -> null
        }

        Log.d("ecemm", "Item clicked: $item")
        Log.d("ecemm", "FoodDescription created: ${foodDescription?.text}")

        foodDescription?.let {
            val bundle = Bundle().apply {
                putParcelable("foodDescription", it)
            }

            // FragmentTransaction ile geçiş yapıyoruz
            val appleFragment = AppleFragment().apply {
                arguments = bundle
            }

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, appleFragment)
                .addToBackStack(null) // Geri tuşuyla geri dönmek için
                .commit() // Fragment state kayıplarını önle
            Log.d("ecemm", "Navigated to AppleFragment manually")

        } ?: Log.e("ecemm", "FoodDescription is null!")
    }

}

