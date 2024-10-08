package com.melo.dogify.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.melo.dogify.databinding.ItemSafeOnesBinding
import com.melo.dogify.model.FoodModel


class SafeOnesAdapter(
    private val context: Context,
    private val itemClickListener: ItemClickListener,
) : ListAdapter<FoodModel, SafeOnesAdapter.SafeOnesViewHolder>(DiffCallback()) {

    interface ItemClickListener {
        fun onItemClick(item: FoodModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SafeOnesViewHolder {
        val binding = ItemSafeOnesBinding.inflate(LayoutInflater.from(context), parent, false)
        return SafeOnesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SafeOnesAdapter.SafeOnesViewHolder, position: Int) {
        val foodModel = getItem(position)
        holder.bind(foodModel)
    }

    inner class SafeOnesViewHolder(private val binding: ItemSafeOnesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(foodModel: FoodModel) {
            binding.foodModel = foodModel


            binding.root.setOnClickListener {
                itemClickListener.onItemClick(foodModel)

            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<FoodModel>() {
        override fun areItemsTheSame(oldItem: FoodModel, newItem: FoodModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FoodModel, newItem: FoodModel): Boolean {
            return oldItem == newItem
        }
    }
}
