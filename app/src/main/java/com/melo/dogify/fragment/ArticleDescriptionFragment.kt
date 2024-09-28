package com.melo.dogify.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.melo.dogify.R
import com.melo.dogify.core.fragments.BaseFragment
import com.melo.dogify.databinding.FragmentAppleBinding
import com.melo.dogify.databinding.FragmentArticleDescriptionBinding
import com.melo.dogify.model.ArticleDescriptionModel
import com.melo.dogify.model.FoodDescriptionModel
import com.melo.dogify.viewmodel.SoundsViewModel
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint

class ArticleDescriptionFragment : Fragment() {

    private var _binding: FragmentArticleDescriptionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val articleDescription = arguments?.getParcelable<ArticleDescriptionModel>("articleDescription")

        if (articleDescription != null) {
            binding.articleDescriptionModel = articleDescription
            binding.executePendingBindings()

            binding.title.text = getString(articleDescription.titleText)
            binding.text.text = getString(articleDescription.text)
        } else {
            Log.e("ArticleDescriptionFragment", "FoodDescription is null!")
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
