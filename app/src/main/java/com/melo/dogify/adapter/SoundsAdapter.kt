package com.melo.dogify.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.melo.dogify.R
import com.melo.dogify.model.CardModel
import com.melo.dogify.viewmodel.SoundsViewModel

class SoundsAdapter(
    private val context: Context,
    var itemClickListener: ItemClickListener
) : RecyclerView.Adapter<SoundsAdapter.SoundsViewHolder>() {

    private var items: List<CardModel> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SoundsViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_list,
            parent,
            false
        )
        return SoundsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SoundsViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(item)
        }
    }

    override fun getItemCount(): Int = items.size

    fun submitList(cardList: List<CardModel>) {
        items = cardList
        notifyDataSetChanged()
    }

    inner class SoundsViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CardModel) {
            binding.executePendingBindings()
        }
    }

    interface ItemClickListener {
        fun onItemClick(item: CardModel)
        fun viewModelClass(): Class<SoundsViewModel>
    }
}
