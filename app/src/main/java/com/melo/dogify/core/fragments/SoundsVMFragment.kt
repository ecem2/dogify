package com.melo.dogify.core.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.melo.dogify.viewmodel.DogBaseViewModel

abstract class SoundsVMFragment<VM : DogBaseViewModel> :
    SoundsBaseFragment() {

    protected lateinit var viewModel: VM

    abstract fun viewModelClass(): Class<VM>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = createViewModel()
    }

    private fun createViewModel(): VM {
        return ViewModelProvider(this)[viewModelClass()]
    }
}