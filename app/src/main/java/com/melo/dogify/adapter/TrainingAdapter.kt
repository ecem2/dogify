package com.melo.dogify.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.melo.dogify.R
import com.melo.dogify.databinding.ItemListBinding
import com.melo.dogify.databinding.ItemTrainingBinding
import com.melo.dogify.model.CardModel
import com.melo.dogify.model.TrainingModel
import com.melo.dogify.viewmodel.SoundsViewModel


class TrainingAdapter(
    private val context: Context,
    private val itemClickListener: ItemClickListener
) : ListAdapter<TrainingModel, TrainingAdapter.TrainingViewHolder>(DiffCallback()) {

    interface ItemClickListener {
        fun onItemClick(item: TrainingModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        val binding = ItemTrainingBinding.inflate(LayoutInflater.from(context), parent, false)
        return TrainingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        val trainingModel = getItem(position)
        holder.bind(trainingModel)
    }

    inner class TrainingViewHolder(private val binding: ItemTrainingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(trainingModel: TrainingModel) {
            binding.trainingModel = trainingModel


            binding.root.setOnClickListener {
                itemClickListener.onItemClick(trainingModel)

            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<TrainingModel>() {
        override fun areItemsTheSame(oldItem: TrainingModel, newItem: TrainingModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TrainingModel, newItem: TrainingModel): Boolean {
            return oldItem == newItem
        }
    }
}
