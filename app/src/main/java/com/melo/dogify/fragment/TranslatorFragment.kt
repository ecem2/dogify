package com.melo.dogify.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.melo.dogify.R
import com.melo.dogify.viewmodel.TranslatorViewModel

class TranslatorFragment : Fragment() {

    companion object {
        fun newInstance() = TranslatorFragment()
    }

    private lateinit var viewModel: TranslatorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_translator, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TranslatorViewModel::class.java)
        // TODO: Use the ViewModel
    }

}