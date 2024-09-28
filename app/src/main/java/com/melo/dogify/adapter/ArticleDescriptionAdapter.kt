package com.melo.dogify.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.melo.dogify.databinding.ItemArticleBinding
import com.melo.dogify.model.ArticleDescriptionModel
import com.melo.dogify.model.ArticleModel

class ArticleDescriptionAdapter(
    private val context: Context,
    private val itemClickListener: ItemClickListener
) : ListAdapter<ArticleDescriptionModel, ArticleDescriptionAdapter.ArticleViewHolder>(DiffCallback()) {

    interface ItemClickListener {
        fun onItemClick(item: ArticleDescriptionModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val trainingModel = getItem(position)
        holder.bind(trainingModel)
    }

    inner class ArticleViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(articleModel: ArticleModel) {
            binding.articleModel = articleModel


            binding.root.setOnClickListener {
                itemClickListener.onItemClick(articleModel)

            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ArticleModel>() {
        override fun areItemsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
            return oldItem == newItem
        }
    }
}