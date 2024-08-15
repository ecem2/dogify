package com.melo.dogify.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.melo.dogify.R
import com.melo.dogify.viewmodel.WhistleViewModel

class WhistleFragment : Fragment() {

    companion object {
        fun newInstance() = WhistleFragment()
    }

    private lateinit var viewModel: WhistleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_whistle, container, false)
    }

}