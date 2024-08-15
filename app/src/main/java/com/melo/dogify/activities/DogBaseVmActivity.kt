package com.melo.dogify.activities

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.melo.dogify.viewmodel.DogBaseViewModel

abstract class DogBaseVmActivity<VM : DogBaseViewModel> : DogBaseActivity() {

    protected lateinit var viewModel: VM

    abstract fun viewModelClass(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[viewModelClass()]
    }
}