package com.melo.dogify.core.fragments

import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.WindowManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.melo.dogify.activities.DogBaseVmDbActivity
import com.melo.dogify.core.viewmodel.BaseViewModel
import com.melo.dogify.viewmodel.DogBaseViewModel
import com.melo.dogify.viewmodel.SoundsViewModel

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
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    fun showProgress() {
        dialog?.show()
    }

    fun hideProgress() {
        dialog?.dismiss()
    }



}