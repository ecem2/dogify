package com.melo.dogify.onboard

import com.melo.dogify.R
import com.melo.dogify.core.fragments.BaseFragment
import com.melo.dogify.databinding.FragmentOnboardTwoBinding
import com.melo.dogify.extensions.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardTwoFragment : BaseFragment<OnboardViewModel, FragmentOnboardTwoBinding>() {

    override fun viewModelClass() = OnboardViewModel::class.java

    override fun getResourceLayoutId() = R.layout.fragment_onboard_two

    override fun onInitDataBinding() {
        viewBinding.imgBtn.setOnClickListener {
            viewModel.saveOnBoardingState()
            launchHomeScreen()
        }
    }

    private fun launchHomeScreen() {
        navigate(OnboardTwoFragmentDirections.actionOnboardTwoFragmentToSoundsFragment())

    }
}