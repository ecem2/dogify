package com.melo.dogify.onboard


import com.melo.dogify.R
import com.melo.dogify.core.fragments.BaseFragment
import com.melo.dogify.databinding.FragmentOnboardBinding
import com.melo.dogify.extensions.handleOnBackPressed
import com.melo.dogify.extensions.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardFragment : BaseFragment<OnboardViewModel, FragmentOnboardBinding>() {

    override fun viewModelClass() = OnboardViewModel::class.java

    override fun getResourceLayoutId() = R.layout.fragment_onboard

    override fun onInitDataBinding() {
        handleOnBackPressed {
            requireActivity().finish()
        }
        viewModel.saveOnBoardingState()
        launchHomeScreen()
    }

    private fun launchHomeScreen(){
        viewBinding.imgBtn.setOnClickListener {
           navigate(OnboardFragmentDirections.actionOnboardFragmentToOnboardTwoFragment())
        }
    }

}