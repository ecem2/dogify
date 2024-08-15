package com.melo.dogify

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class DogBaseViewHolder<T : ViewDataBinding>(
    val binding: T
) : RecyclerView.ViewHolder(binding.root)