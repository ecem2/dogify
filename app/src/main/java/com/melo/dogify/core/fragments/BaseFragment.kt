package com.melo.dogify.core.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.ViewDataBinding
import com.melo.dogify.viewmodel.DogBaseViewModel

abstract class BaseFragment<VM : DogBaseViewModel, DB : ViewDataBinding> :
    SoundsVmDbFragment<VM, DB>() {
    var dialog: Dialog? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onInitDataBinding()
    }

    abstract fun onInitDataBinding()

    fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(requireActivity().window, false)
        WindowInsetsControllerCompat(requireActivity().window, viewBinding.root).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    fun showProgress() {
        dialog?.show()
    }

    fun hideProgress() {
        dialog?.dismiss()
    }
}