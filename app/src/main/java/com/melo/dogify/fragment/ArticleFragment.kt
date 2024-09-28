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
import com.melo.dogify.model.ArticleDescriptionModel
import com.melo.dogify.model.ArticleModel
import com.melo.dogify.model.CardModel
import com.melo.dogify.model.FoodDescriptionModel
import com.melo.dogify.model.FoodModel
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
        val articleDescription = when (item) {
            viewModel.articleList.getOrNull(0) -> ArticleDescriptionModel(
                titleText = R.string.first_article,
                text = R.string.first_article_description
            )

            viewModel.articleList.getOrNull(1) -> ArticleDescriptionModel(
                titleText = R.string.second_article,
                text = R.string.second_article_description
            )

            viewModel.articleList.getOrNull(2) -> ArticleDescriptionModel(
                titleText = R.string.third_article,
                text = R.string.third_article_description
            )

            viewModel.articleList.getOrNull(3) -> ArticleDescriptionModel(
                titleText = R.string.fourth_article,
                text = R.string.fourth_article_description

            )

            else -> null

        }
        articleDescription?.let {
            val bundle = Bundle().apply {
                putParcelable("articleDescription", it)
            }

            // FragmentTransaction ile geçiş yapıyoruz
            val articleDescriptionFragment = ArticleDescriptionFragment().apply {
                arguments = bundle
            }

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, articleDescriptionFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}