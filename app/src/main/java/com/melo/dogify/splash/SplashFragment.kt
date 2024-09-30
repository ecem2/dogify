package com.melo.dogify.splash


import com.melo.dogify.R
import com.melo.dogify.core.fragments.BaseFragment
import com.melo.dogify.databinding.FragmentSplashBinding
import com.melo.dogify.extensions.navigate
import com.melo.dogify.viewmodel.SoundsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<SoundsViewModel, FragmentSplashBinding>() {

    override fun onInitDataBinding() {
        checkFirstLaunch()
    }

    private fun checkFirstLaunch() {
        if (viewModel.preferences.getFirstLaunch()) {
            navigate(SplashFragmentDirections.actionSplashFragmentToOnboardFragment())
        } else {
            navigate(
                SplashFragmentDirections.actionSplashFragmentToSoundsFragment())

        }
    }

    override fun viewModelClass() = SoundsViewModel::class.java
    override fun getResourceLayoutId() = R.layout.fragment_splash
}