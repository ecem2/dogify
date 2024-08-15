package com.melo.dogify.activities

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.databinding.ViewDataBinding
import com.melo.dogify.core.common.ViewBindingUtil
import com.melo.dogify.core.viewmodel.BaseViewModel
import com.melo.dogify.databinding.ActivityBaseBinding

abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> :
    DogBaseVmDbActivity<VM, DB>() {

    private lateinit var baseViewBinding: ViewDataBinding

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setResourceViewBinding(): View {
        baseViewBinding =
            ViewBindingUtil.inflate<ActivityBaseBinding>(layoutInflater)
        viewBinding = ViewBindingUtil.inflate(
            layoutInflater,
            (baseViewBinding as ActivityBaseBinding).baseContentFrame,
            true,
            viewDataBindingClass()
        )
        viewBinding.lifecycleOwner = this
        return baseViewBinding.root
    }
}