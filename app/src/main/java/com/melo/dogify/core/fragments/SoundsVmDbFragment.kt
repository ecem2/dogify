package com.melo.dogify.core.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.melo.dogify.viewmodel.DogBaseViewModel

abstract class SoundsVmDbFragment<VM : DogBaseViewModel, DB : ViewDataBinding> :
    SoundsVMFragment<VM>() {

    protected lateinit var viewBinding: DB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater, getResourceLayoutId(), container, false)
        viewBinding.lifecycleOwner = viewLifecycleOwner
        return viewBinding.root
    }

}