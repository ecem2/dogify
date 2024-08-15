package com.melo.dogify.activities

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.melo.dogify.core.common.ViewBindingUtil
import com.melo.dogify.databinding.ActivityBaseBinding
import com.melo.dogify.databinding.ActivityDogBaseBinding
import com.melo.dogify.viewmodel.DogBaseViewModel

abstract class DogBaseVmDbActivity<VM : DogBaseViewModel, DB : ViewDataBinding> :
    DogBaseVmActivity<VM>() {

    protected lateinit var viewBinding: DB

    abstract fun viewDataBindingClass(): Class<DB>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = setResourceViewBinding()
        setContentView(view)
        viewBinding.lifecycleOwner = this
        onInitDataBinding()
    }

    open fun setResourceViewBinding(): View {
        val baseViewBinding = ViewBindingUtil.inflate<ActivityDogBaseBinding>(layoutInflater)
        viewBinding = ViewBindingUtil.inflate(
            layoutInflater,
            baseViewBinding.baseDogContentFrame,
            true,
            viewDataBindingClass()
        )
        return baseViewBinding.root
    }

    abstract fun onInitDataBinding()
}