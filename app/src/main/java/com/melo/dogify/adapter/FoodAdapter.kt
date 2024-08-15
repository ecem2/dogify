package com.melo.dogify.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.melo.dogify.databinding.ItemFoodBinding
import com.melo.dogify.model.CardModel
import com.melo.dogify.viewmodel.SoundsViewModel


class FoodAdapter(
    private val context: Context,
    private val itemClickListener: ItemClickListener
) : ListAdapter<CardModel, FoodAdapter.FoodViewHolder>(DiffCallback()) {

    interface ItemClickListener {
        fun onItemClick(item: CardModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(context), parent, false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val cardModel = getItem(position)
        holder.bind(cardModel)
    }

    inner class FoodViewHolder(private val binding: ItemFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cardModel: CardModel) {
            binding.cardModel = cardModel
            // binding.textView.text = cardModel.text


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
