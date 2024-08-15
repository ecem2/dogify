package com.melo.dogify.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.melo.dogify.R
import com.melo.dogify.adapter.ArticleAdapter
import com.melo.dogify.adapter.SoundsAdapter
import com.melo.dogify.core.fragments.BaseFragment
import com.melo.dogify.databinding.FragmentArticleBinding
import com.melo.dogify.databinding.FragmentSoundsBinding
import com.melo.dogify.model.ArticleModel
import com.melo.dogify.model.CardModel
import com.melo.dogify.model.TrainingModel
import com.melo.dogify.viewmodel.SoundsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint

class ArticleFragment : BaseFragment<SoundsViewModel, FragmentArticleBinding>(),
    ArticleAdapter.ItemClickListener {

    private val articleAdapter: ArticleAdapter by lazy {
        ArticleAdapter(
            requireContext(),
            this@ArticleFragment

        )
    }

    private var selectedArticle: ArticleModel? = null
    private var selectedItemPosition: Int = 0


    override fun viewModelClass() = SoundsViewModel::class.java

    override fun getResourceLayoutId() = R.layout.fragment_article

    override fun onInitDataBinding() {
        setupArticleStyle()
    }

    private fun setupArticleStyle() {
        viewBinding.rvArticle.apply {
            adapter = articleAdapter
            setHasFixedSize(true)
        }
        articleAdapter.submitList(viewModel.articleList)

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onItemClick(item: ArticleModel) {
        selectedItemPosition = viewModel.articleList.indexOf(item)
        selectedArticle = item
        articleAdapter.notifyDataSetChanged()
    }
}