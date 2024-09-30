package com.melo.dogify.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.melo.dogify.databinding.ItemListBinding
import com.melo.dogify.model.CardModel


class SoundsAdapter(
    private val context: Context,
    private val itemClickListener: ItemClickListener,
) : ListAdapter<CardModel, SoundsAdapter.SoundsViewHolder>(DiffCallback()) {

    interface ItemClickListener {
        fun onItemClick(item: CardModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundsViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(context), parent, false)
        return SoundsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SoundsViewHolder, position: Int) {
        val cardModel = getItem(position)
        holder.bind(cardModel)
    }

    inner class SoundsViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cardModel: CardModel) {
            binding.cardModel = cardModel


            binding.root.setOnClickListener {
                itemClickListener.onItemClick(cardModel)

            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<CardModel>() {
        override fun areItemsTheSame(oldItem: CardModel, newItem: CardModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CardModel, newItem: CardModel): Boolean {
            return oldItem == newItem
        }
    }
}
